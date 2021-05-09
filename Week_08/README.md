作业

##### 1、设计对前面的订单表数据进行水平分库分表，拆分2个库，每个库16张表。 并在新结构在演示常见的增删改查操作。代码、sql 和配置文件，上传到 Github。

使用ShardingSphere-Proxy作为中间件，配置在config-sharding.yaml和server.yaml中，建表语句t_order.sql，

代码在工程MysqlShardingProxy中

启动ShardingSphere-Proxy，然后在MysqlShardingProxy中配置连接到3307端口的数据库

##### 2、基于 hmily TCC 或 ShardingSphere 的 Atomikos XA 实现一个简单的分布 式事务应用 demo（二选一），提交到 Github。

