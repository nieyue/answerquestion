# 数据库 
#创建数据库
DROP DATABASE IF EXISTS answerquestion_db;
CREATE DATABASE answerquestion_db;

#使用数据库 
use answerquestion_db;
#创建角色表
CREATE TABLE role_tb(
role_id int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
name varchar(255) COMMENT '角色名',
duty varchar(255) COMMENT '角色职责',
update_date datetime COMMENT '更新时间',
PRIMARY KEY (role_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='角色表';

#创建账户表
CREATE TABLE account_tb(
account_id int(11) NOT NULL AUTO_INCREMENT COMMENT '账户id',
realname varchar(255) COMMENT '姓名',
sid varchar(255) COMMENT '学号',
sex tinyint(4) DEFAULT 0 COMMENT '性别,默认为0未知，为1男性，为2女性',
nation varchar(255) COMMENT '民族',
identity_cards varchar(255) COMMENT '身份证',
phone varchar(255) COMMENT '联系电话',
password varchar(255) COMMENT '密码',
icon varchar(255) COMMENT '头像',
birth_date datetime COMMENT '出生年月日',
create_date datetime COMMENT '创建时间',
login_date datetime COMMENT '登陆时间',
status tinyint DEFAULT 0 COMMENT '状态，默认0正常，1封禁，2异常',
role_id int(11) COMMENT '角色id外键',
PRIMARY KEY (account_id),
INDEX INDEX_REALNAME (realname) USING BTREE,
INDEX INDEX_ROLEID (role_id) USING BTREE,
INDEX INDEX_STATUS (status) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='账户表';

#创建学院表
CREATE TABLE academy_tb(
academy_id int(11) NOT NULL AUTO_INCREMENT COMMENT '学院id',
name varchar(255) COMMENT '名称',
duty varchar(255) COMMENT '描述',
create_date datetime COMMENT '创建时间',
update_date datetime COMMENT '更新时间',
PRIMARY KEY (academy_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='学院表';

#创建专业表
CREATE TABLE major_tb(
major_id int(11) NOT NULL AUTO_INCREMENT COMMENT '专业id',
name varchar(255) COMMENT '名称',
duty varchar(255) COMMENT '描述',
create_date datetime COMMENT '创建时间',
update_date datetime COMMENT '更新时间',
academy_id int(11) COMMENT '学院id外键',
PRIMARY KEY (major_id),
INDEX INDEX_ACADEMYID (academy_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='专业表';

#创建课程表
CREATE TABLE course_tb(
course_id int(11) NOT NULL AUTO_INCREMENT COMMENT '课程id',
name varchar(255) COMMENT '名称',
duty varchar(255) COMMENT '描述',
create_date datetime COMMENT '创建时间',
update_date datetime COMMENT '更新时间',
major_id int(11) COMMENT '专业id外键',
PRIMARY KEY (course_id),
INDEX INDEX_MAJORID (major_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='课程表';

#创建学生课程表
CREATE TABLE student_course_tb(
student_course_id int(11) NOT NULL AUTO_INCREMENT COMMENT '学生课程id',
name varchar(255) COMMENT '名称',
duty varchar(255) COMMENT '描述',
create_date datetime COMMENT '创建时间',
update_date datetime COMMENT '更新时间',
course_id int(11) COMMENT '课程id外键',
account_id int(11) COMMENT '账户id外键',
PRIMARY KEY (student_course_id),
INDEX INDEX_COURSEID (course_id) USING BTREE,
INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='学生课程表';

#创建问题表
CREATE TABLE problem_tb(
problem_id int(11) NOT NULL AUTO_INCREMENT COMMENT '问题id',
title varchar(255) COMMENT '标题',
content longtext COMMENT '内容',
create_date datetime COMMENT '创建时间',
update_date datetime COMMENT '更新时间',
student_course_id int(11) COMMENT '学生课程id外键',
answer_id int(11) COMMENT '采纳回答id外键',
PRIMARY KEY (problem_id),
INDEX INDEX_STUDENTCOURSEID (student_course_id) USING BTREE,
INDEX INDEX_ANSWERID (answer_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='问题表';

#创建回答表
CREATE TABLE answer_tb(
answer_id int(11) NOT NULL AUTO_INCREMENT COMMENT '回答id',
title varchar(255) COMMENT '标题',
content longtext COMMENT '内容',
create_date datetime COMMENT '创建时间',
update_date datetime COMMENT '更新时间',
problem_id int(11) COMMENT '问题id外键',
PRIMARY KEY (answer_id),
INDEX INDEX_PROBLEMID (problem_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='回答表';

#创建资料表
CREATE TABLE datum_tb(
datum_id int(11) NOT NULL AUTO_INCREMENT COMMENT '资料id',
title varchar(255) COMMENT '标题',
link varchar(255) COMMENT '链接',
create_date datetime COMMENT '创建时间',
update_date datetime COMMENT '更新时间',
account_id int(11) COMMENT '账户id外键',
PRIMARY KEY (datum_id),
INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='资料表';

#设置初始角色
INSERT IGNORE INTO role_tb (role_id,name,duty,update_date)
VALUES (1000,"超级管理员","超级管理员",now());
INSERT IGNORE INTO role_tb (role_id,name,duty,update_date)
VALUES (1001,"教师","教师",now());
INSERT IGNORE INTO role_tb (role_id,name,duty,update_date)
VALUES (1002,"学生","学生",now());

#设置初始管理员密码MD5加密123456
INSERT IGNORE INTO account_tb (account_id,realname,sid,phone,password,create_date,login_date,role_id)
VALUES (1000,"超级管理员","1000","1000","11874bb6149dd45428da628c9766b252",now(),now(),1000);