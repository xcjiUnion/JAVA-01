package org.geekbang.time.insert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.geekbang.time.dao.OrderExtMapper;
import org.geekbang.time.po.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 多线程批量插入
 * @author jixch
 *
 */
@Service
public class MultiThreadBatchInsert {

	@Autowired
	OrderExtMapper orderExtMapper;
	
	public void batchInsert() {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		int begin = 1;
		int end = 0;
		for(int i = 1; i <= 10; i++) {
			end = i * 100000;
			executorService.execute(new BatchInner(begin, end));
			
			begin = end + 1;
		}
		
	}
	
	class BatchInner implements Runnable {

		int begin;
		int end;
		
		public BatchInner(int begin, int end) {
			this.begin = begin;
			this.end = end;
		}
		
		@Override
		public void run() {
			long startTime = System.currentTimeMillis();
			List<Order> recordList = new ArrayList<>();
			for(int i = begin; i <= end; i++) {
				Order record = new Order();
				record.setOrderId("ordernumer_" + i);
				record.setAmount(new BigDecimal(10000 + i));
				record.setStatus("1");
				record.setCreateTime(new Date());
				record.setUpdateTime(new Date());
				recordList.add(record);
				
				//每一万次插入一次数据到数据库
				if(i % 10000 == 0) {
					orderExtMapper.batchInsertOrder(recordList);
					recordList = new ArrayList<>();
				}
			}
			long endTime = System.currentTimeMillis();
			System.out.println("耗时:" + (endTime - startTime) + "ms");
		}
		
	}
}
