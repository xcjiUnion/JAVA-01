drop table if exists t_user;
drop table if exists t_order;
drop table if exists t_product;

drop table if exists t_user_order_relation;
drop table if exists t_order_product_relation;
/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   int(11) not null auto_increment comment '自增id',
   user_id              varchar(100) not null comment '用户id',
   user_name            varchar(200) not null comment '用户名称',
   password             varchar(100) not null comment '密码,加密保存',
   id_number            varchar(18) comment '身份证号码',
   level                varchar(6) comment '等级, common-普通用户, vip-vip用户, svip-超级vip用户',
   email                varchar(50) comment '邮箱',
   telephone            varchar(10) comment '手机号码',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_user comment '用户表';

create index idx_user_id on t_user
(
   user_id
);

/*==============================================================*/
/* Table: t_order                                               */
/*==============================================================*/
create table t_order
(
   order_id             varchar(32) not null comment '订单id',
   amount               decimal(8,4) not null comment '金额',
   status               char(1) not null comment '状态, 1-待付款,2-已付款,3-待发货,4-已发货,5-已签收, 0-作废,',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   primary key (order_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_order comment '订单表';

create table t_user_order_relation
(
   user_id              varchar(100) not null comment '用户id',
   order_id             varchar(32) not null comment '订单id',
   primary key (user_id, order_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_user_order_relation comment '用户订单关系表';



/*==============================================================*/
/* Table: t_product                                             */
/*==============================================================*/
create table t_product
(
   product_code         varchar(32) not null comment '商品编码',
   product_name         varchar(300) not null comment '商品名称',
   classify             varchar(20) not null comment '分类',
   weight               decimal(18,4) not null comment '重量',
   unit                 varchar(10) not null comment '单位',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   primary key (product_code)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_product comment '商品表';

create table t_order_product_relation
(
   order_id             varchar(32) not null comment '订单id',
   product_code         varchar(32) not null comment '商品编码',
   primary key (order_id, product_code)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_order_product_relation comment '订单商品关系表';
