package org.geekbang.time.homework0501.annotation;

import javax.annotation.Resource;

import org.geekbang.time.homework0501.xml.Food;
import org.geekbang.time.homework0501.xml.People;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;

@Data
public class House {

	@Autowired
	People people1;

	@Resource(name = "food02")
	Food food02;

	public void living() {
		System.out.println("There are " + this.people1.getFoods().size() + " foods and one is " + this.food02);
	}
}
