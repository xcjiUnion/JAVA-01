package org.geekbang.time.homework0501.xml;

import java.util.List;

import lombok.Data;

@Data
public class People {

	List<Food> foods;
	
	public void eating() {
		System.out.println(this.getFoods());
	}
}
