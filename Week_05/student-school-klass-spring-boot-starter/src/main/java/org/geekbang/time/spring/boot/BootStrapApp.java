package org.geekbang.time.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

//@SpringBootApplication
@EnableAutoConfiguration
public class BootStrapApp {

	public static void main(String[] args) {
		SpringApplication.run(BootStrapApp.class, args);
	}

}
