作业

##### 1、按自己设计的表结构，插入100万订单模拟数据，测试不同方式的插入效率。

项目代码：MysqlInsert

```
表：
CREATE TABLE `t_order` (
  `order_id` varchar(32) NOT NULL COMMENT '订单id',
  `amount` decimal(18,4) NOT NULL COMMENT '金额',
  `status` char(1) NOT NULL COMMENT '状态, 1-待付款,2-已付款,3-待发货,4-已发货,5-已签收, 0-作废,',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表'
```

有下面几种方式：

（1）用代码生成出100万条数据，使用mysqlimport或load data命令导入数据

使用GeneratorData.java生成100万条数据，耗时：819毫秒;

使用mysqlimport -u root -p --local --fields-terminated-by="\t" --lines-terminated-by="\n"  db_0 "t_order.txt" 导入耗时：24.61秒

使用 LOAD DATA LOCAL INFILE 't_order.txt' INTO TABLE t_order 导入，耗时：25.25秒

（2）采用单线程批量插入

代码：SingleThreadBatchInsert.java，耗时：55.11秒

（3）采用多线程批量插入

代码：MultiThreadBatchInsert.java，采用10个线程跑，每个线程跑10万条，最大耗时：26.39秒

##### 2、读写分离-动态切换数据源版本1.0

代码所在项目：MysqlInsert

配置文件：application-dynamic.yml

##### 3、读写分离-数据库框架版本2.0

代码项目：MysqlShardingSphere