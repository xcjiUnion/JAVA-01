package org.geekbang.time;

import org.geekbang.time.dao.OrderMapper;
import org.geekbang.time.po.Order;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@MapperScan("org.geekbang.time.dao")
public class MysqlShardingProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysqlShardingProxyApplication.class, args);
	}
	
	@Component
	public class StartUp implements CommandLineRunner {

		@Autowired
		OrderMapper orderMapper;
		
		@Override
		public void run(String... args) throws Exception {
			System.out.println("--startup---");
		
			//新增
			Order order2 = new Order();
			order2.setOrderId(100L);
			order2.setUserId(100);
			order2.setRemark("0_100");
			orderMapper.insert(order2);
			
			//查询
			Order order3 = orderMapper.selectByPrimaryKey(100L);
			if(order3 != null) {
				System.out.println(order3.toString());
			} else {
				System.out.println("------null---");
			}
			
			//修改
			Order order4 = new Order();
			order4.setOrderId(100L);
			order4.setUserId(100);
			order4.setRemark("修改");
			orderMapper.updateByPrimaryKeySelective(order4);
			
			//删除
			orderMapper.deleteByPrimaryKey(100L);
		}
		
	}

}
