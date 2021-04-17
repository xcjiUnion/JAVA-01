package org.geekbang.time;

import java.math.BigDecimal;

import org.geekbang.time.dao.OrderExtMapper;
import org.geekbang.time.po.Order;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@MapperScan("org.geekbang.time.dao")
public class MysqlShardingSphereApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysqlShardingSphereApplication.class, args);
	}
	
	@Component
	public class StartUp implements CommandLineRunner {

		@Autowired
		OrderExtMapper orderExtMapper;
		
		@Override
		public void run(String... args) throws Exception {
			System.out.println("--startup---");
			Order order = orderExtMapper.selectByPrimaryKey("ordernumer_1");
			System.out.println(order.toString());
			
			
			Order order2 = new Order();
			order2.setOrderId("test0001");
			order2.setAmount(new BigDecimal(100));
			order2.setStatus("2");
			orderExtMapper.insertSelective(order2);
			
			Order order3 = orderExtMapper.selectByPrimaryKey("test0001");
			if(order3 != null) {
				System.out.println(order3.toString());
			} else {
				System.out.println("------null---");
			}
		}
		
	}

}
