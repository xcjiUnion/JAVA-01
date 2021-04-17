package org.geekbang.time;

import org.geekbang.time.dao.OrderExtMapper;
import org.geekbang.time.insert.MultiThreadBatchInsert;
import org.geekbang.time.insert.SingleThreadBatchInsert;
import org.geekbang.time.po.Order;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@MapperScan("org.geekbang.time.dao")
public class MysqlInsertApplication {


	public static void main(String[] args) {
		SpringApplication.run(MysqlInsertApplication.class, args);
	}
	
	@Component
	public class StartUp implements CommandLineRunner {

		@Autowired
		SingleThreadBatchInsert singleThreadBatchInsert;
		
		@Autowired
		MultiThreadBatchInsert multiThreadBatchInsert;
		
		@Autowired
		OrderExtMapper orderExtMapper;
		
		@Override
		public void run(String... args) throws Exception {
			System.out.println("--startup---");
			Order order = orderExtMapper.selectByPrimaryKey("ordernumer_1");
			System.out.println(order.toString());
			
//			singleThreadBatchInsert.insert();
//			multiThreadBatchInsert.batchInsert();
			
		}
		
	}

}
