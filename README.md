## 弦思社区

##资料  
[Spring文档](spring.io/guides)  
[Spring Web](spring.io/guides/gs/serving-web-content/)  
[bootstrap](v3.bootcss.com/getting-started/)  
##工具  
[Git](git-scm.com)  
##脚本  
```sql
CREATE TABLE user (
	id INT NOT NULL,
	account_id VARCHAR(100),
	name VARCHAR(50),
	token CHAR(36),
	gmt_create BIGINT,
	gmt_modified BIGINT
)
go
use xiansi;
alter table user add bio varchar(256) null;
go

use xiansi;
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
