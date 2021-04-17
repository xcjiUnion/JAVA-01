package org.geekbang.time.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.geekbang.time.po.Order;

public interface OrderExtMapper extends OrderMapper {

	int batchInsertOrder(@Param("recordList") List<Order> recordList);
}
