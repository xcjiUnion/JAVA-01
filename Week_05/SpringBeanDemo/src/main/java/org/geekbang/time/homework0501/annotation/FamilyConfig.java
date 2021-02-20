package org.geekbang.time.homework0501.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FamilyConfig {

	@Bean
	public House createNewHouse() {
		House house = new House();
		System.out.println(house);
		return house;
	}
}
