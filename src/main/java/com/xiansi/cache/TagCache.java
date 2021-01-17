package com.xiansi.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.xiansi.dto.TagDTO;

public class TagCache {
	public static List<TagDTO> get(){
		List<TagDTO> tagDTOS = new ArrayList<TagDTO>();
		TagDTO program = new TagDTO();
		program.setCategoryName("开发语言");
		program.setTags(Arrays.asList("JS", "PHP", "CSS", "HTML", "HTML5", 
				"Java", "Node.js", "Python", "c++", "C", "Golang", "Shell", "Swift", "c#", "Sass", "Ruby", "Bash", "Less", 
				"Asp.Net", "Lua", "Scala", "Coffeescript", "Actionscript", "Rust", "Erlang", "Perl"));
		tagDTOS.add(program);
		TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("Laravel", "Spring", "Express", "Django", "Flask", "Yii", 
        		"Ruby-on-rails", "Tornado", "Koa", "Struts"));
        tagDTOS.add(framework);
        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("Linux", "Nginx", "Docker", "Apache", "Ubuntu", "Centos",
        		"缓存 Tomcat", "负载均衡", "Unix", "Hadoop", "Windows-Server"));
        tagDTOS.add(server);

        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("Mysql", "Redis", "Mongodb", "Sql", "Oracle", 
        		"Nosql memcached", "SqlServer", "Postgresql", "Sqlite"));
        tagDTOS.add(db);

        TagDTO tool = new TagDTO();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("Git", "Github", "VS-code", "Vim", 
        		"Sublime-Text", "Xcode Intellij-Idea", "Eclipse", "maven", "ide", "svn", 
        		"visual-studio", "Atom Emacs"));
        tagDTOS.add(tool);
		return tagDTOS;
	}
	
	public static String filterInvalid(String tags) {
		String[] split = StringUtils.split(tags, "，");
		List<TagDTO> tagDTOS = get();
		List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream())
				.collect(Collectors.toList());
		String invalid = Arrays.stream(split).filter(t -> StringUtils.isBlank(t) || !tagList.contains(t))
				.collect(Collectors.joining("，"));
        return invalid;
	}
}
