create table user (
    id int(11) not null auto_increment comment '主键id',
	user_name varchar(100) not null comment '用户名',
	sex char(1) not null default '男' comment '性别',
	age int(11) not null comment '年龄',
	primary key(id)
) ENGINE=InnoDB comment='用户表';