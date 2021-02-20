package org.geekbang.time.homework0501.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Food {

	private String dish;
	private String name;
	
	public void init() {
		System.out.println("cooking........");
	}
	
	public Food create() {
		return new Food("第一道菜", "宫保鸡丁");
	}
}
