package org.geekbang.time.homework0501;

import org.geekbang.time.homework0501.annotation.House;
import org.geekbang.time.homework0501.xml.Food;
import org.geekbang.time.homework0501.xml.People;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemo {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		Food food02 = (Food)context.getBean("food02");
		System.out.println(food02.toString());
		
		Food food03 = (Food)context.getBean("food03");
		System.out.println(food03.toString());
		
		People people1 = context.getBean(People.class);
		System.out.println(people1);
		
		House house = context.getBean(House.class);
		System.out.println(house);
		
		house.living();
		
		people1.eating();
		
        System.out.println("   context.getBeanDefinitionNames() ===>> "+ String.join(",", context.getBeanDefinitionNames()));
		
	}

}
