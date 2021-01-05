## 弦思社区

##资料  
[Spring文档](spring.io/guides)  
[Spring Web](spring.io/guides/gs/serving-web-content/)  
[bootstrap](v3.bootcss.com/getting-started/)  
[lombok](projectlombok.org)   
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
	id int auto_increment primary key,
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
```  
##Bug
1.前端无法以列表显示后台的数据，th:each不知道哪里出了问题  。
2.使用questionList拿到的user数据，avatar_url属性没有值。  