package com.xiansi.service;

import java.util.ArrayList;
import java.util.List;

import java.util.Objects;


import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiansi.dto.NotificationDTO;
import com.xiansi.dto.PaginationDTO;
import com.xiansi.enums.NotificationStatusEnum;
import com.xiansi.enums.NotificationTypeEnum;
import com.xiansi.exception.CustomizeErrorCode;
import com.xiansi.exception.CustomizeException;
import com.xiansi.mapper.NotificationMapper;

import com.xiansi.model.Notification;
import com.xiansi.model.NotificationExample;
import com.xiansi.model.User;


@Service
public class NotificationService {
	@Autowired
	private NotificationMapper notificationMapper;
	public PaginationDTO<NotificationDTO> list(Integer id, Integer page, Integer size) {
		PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<NotificationDTO>();
		Integer totalPage;
		NotificationExample notificationExample = new NotificationExample();
		notificationExample.createCriteria().andReceiverEqualTo(id);
		Integer totalCount =(int) notificationMapper.countByExample(notificationExample);

		if (totalCount % size == 0) {
			totalPage = totalCount / size;
		} else {
			totalPage = totalCount / size + 1;
		}

		if (page < 1) {
			page = 1;
		}
		if (page > totalPage) {
			page = totalPage;
		}
		paginationDTO.setPagination(totalPage,page);
		Integer offset = size * (page -1);
		NotificationExample example = new NotificationExample();
		example.createCriteria().andReceiverEqualTo(id);
		example.setOrderByClause("gmt_create desc");
		List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds
				(example, new RowBounds(offset,size));
		
		if (notifications.size() == 0) {
			return paginationDTO;
		}
		List<NotificationDTO> notificationDTOList = new ArrayList<NotificationDTO>();
		for (Notification notification : notifications) {
			NotificationDTO notificationDTO = new NotificationDTO();
			BeanUtils.copyProperties(notification, notificationDTO);
			notificationDTO.setType_name(NotificationTypeEnum.nameOfType(notification.getType()));
			notificationDTOList.add(notificationDTO);
		}
		paginationDTO.setData(notificationDTOList);
		return paginationDTO;
	}
	public Integer unreadCount(Integer userId) {
		NotificationExample notificationExample = new NotificationExample();
		notificationExample.createCriteria().andReceiverEqualTo(userId).andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
		return (int) notificationMapper.countByExample(notificationExample);
		 
	}
	public NotificationDTO read(Integer id, User user) {
		Notification notification = notificationMapper.selectByPrimaryKey(id);
        if (notification == null) {
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if (!Objects.equals(notification.getReceiver(), user.getId())) {
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }

        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setType_name(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
	}
	
}
