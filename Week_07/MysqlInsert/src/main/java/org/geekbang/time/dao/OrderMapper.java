package org.geekbang.time.dao;

import org.geekbang.time.annotation.SelectReadOnly;
import org.geekbang.time.po.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertSelective(Order record);

    @SelectReadOnly
    Order selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}