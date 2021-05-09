package org.geekbang.time;

import org.geekbang.time.config.TransactionConfiguration;
import org.geekbang.time.service.XaOrderService;
import org.geekbang.time.service.XaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@SpringBootApplication
@Import(TransactionConfiguration.class)
public class ShardingSphereAtomikosApplication {

	@Autowired
	XaOrderService xaOrderService;

	@Autowired
	XaUserService XaUserService;

	public static void main(String[] args) {
		SpringApplication.run(ShardingSphereAtomikosApplication.class, args);
	}

	@PostConstruct
	private void xaSuccessTest() {
		XaUserService.insert(1);
		xaOrderService.insert(3);
	}

	@PostConstruct
	private void xaFailedTest() {
		XaUserService.insert(1);
		xaOrderService.insertFailed(3);
	}

}
