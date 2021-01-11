## 弦思社区
## 功能列表  
开源论坛、引入github第三方登录。  
现有功能提问；问答系统 (功能持续更新中)    
## 资料
[Spring 文档](https://spring.io/guides)    
[Spring Web](https://spring.io/guides/gs/serving-web-content/)   
[Github deploy key](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)    
[Bootstrap](https://v3.bootcss.com/getting-started/)    
[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)    
[Spring](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-embedded-database-support)    
[菜鸟教程](https://www.runoob.com/mysql/mysql-insert-query.html)    
[Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values)    
[Spring Dev Tool](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#using-boot-devtools)  
[Spring MVC](https://docs.spring.io/spring/docs/5.0.3.RELEASE/spring-framework-reference/web.html#mvc-handlermapping-interceptor)  
[Markdown 插件](http://editor.md.ipandao.com/)   
[Count(*) VS Count(1)](https://mp.weixin.qq.com/s/Rwpke4BHu7Fz7KOpE2d3Lw)  
[Git](https://git-scm.com/download)   
[Lombok](https://www.projectlombok.org)    
[ctotree](https://www.octotree.io/)   
##工具  
[Git](git-scm.com)  
##脚本  
```sql
CREATE TABLE user (
	id INT NOT NULL auto_increment primary key,
	account_id VARCHAR(100),
	name VARCHAR(50),
	token CHAR(36),
	gmt_create BIGINT,
	gmt_modified BIGINT
);

alter table user add bio varchar(256) null;
alter table user add avatar_url varchar(100) null;  

create table question(
	id int auto_increment primary key not null,
	title varchar(50),
	description text,
	gmt_create bigint,
	gmt_modified bigint,
	creator int,
	comment_count int default 0,
	view_count int default 0,
	like_count int default 0,
	tag varchar(256)
);  
create table comment(
	id int auto_increment primary key not null,
	parent_id int not null,
	type int not null,
	commentator int not null,
	gmt_create bigint not null,
	gmt_modified bigint not null,
	like_count int default 0,
	content varchar(1024)
);  
```
##Bug
1.前端无法以列表显示后台的数据，th:each不知道哪里出了问题(2021/01/06已解决)。   
2.使用questionList拿到的user数据，avatar_url属性没有值(2021/01/06已解决)。    
3.页面删除cookies后，在登录会重复插入sql(2021/01/08已解决)   
4.数据库不存在一条用户数据或者一条信息数据，页面则会报错。在index下无法找到question.user.avatar_url(2021.01.10已解决)  
5.添加逆向工程后，Question类的下Integer 自动绑定为null，导致在刷新页面时，出现404(2021.01.10已解决)   
#Bug修复记录
1.前端无法以列表显示后台数据，原因为th:each中的questions拼写错误。    
2.使用questionList拿到的user数据，avatar_url属性没有值，原因是在application.preperties中添加了mybatis.configuration.map-underscore-to-camel-case=true，改成false即可。   
3.页面删除cookies后或者退出登录后，加入了userService验证，去判断用户是否存在。  
4.插入一条用户数据，重启服务，在html中注释掉question.user.avatar_url，然后在成功登录后，发布一条数据，打开question.user.avatar_url ，刷新页面即可。  
5.Question类的下Integer 自动绑定为null的原因未找到，但是把Question下的Integer变量设置默认值为0 即可正常运行  