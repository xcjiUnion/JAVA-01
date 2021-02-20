package org.geekbang.time.database.jdbc;

public class User {

	private int id;
	private String userName;
	private String sex;
	private int age;
	
	public User(String userName, String sex, int age) {
		this.userName = userName;
		this.sex = sex;
		this.age = age;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
	
}
