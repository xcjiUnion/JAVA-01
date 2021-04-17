package org.geekbang.time.insert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.geekbang.time.dao.OrderExtMapper;
import org.geekbang.time.po.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 单线程批量插入
 * @author jixch
 *
 */
@Service
public class SingleThreadBatchInsert {

	@Autowired
	OrderExtMapper orderExtMapper;
	
	public void insert() {
		long startTime = System.currentTimeMillis();
		List<Order> recordList = new ArrayList<>();
		for(int i = 0; i < 1000000; i++) {
			Order record = new Order();
			record.setOrderId("ordernumer_" + (i + 1));
			record.setAmount(new BigDecimal(10000 + i));
			record.setStatus("1");
			record.setCreateTime(new Date());
			record.setUpdateTime(new Date());
			recordList.add(record);
			
			//每一万次插入一次数据到数据库
			if((i+1) % 10000 == 0) {
				orderExtMapper.batchInsertOrder(recordList);
				recordList = new ArrayList<>();
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("耗时:" + (endTime - startTime) + "ms");
	}
}
