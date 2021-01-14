package com.xiansi.dto;



import lombok.Data;

@Data
public class NotificationDTO {
	 private Integer id;

	 private Long gmt_create;

	 private Integer status;
	 
	 private Integer notifier;
	 
	 private String notifier_name;
	 
	 private String outer_title;
	 
	 private Integer outer_id;
	 
	 private String type_name;
	 
	 private Integer type;
}
