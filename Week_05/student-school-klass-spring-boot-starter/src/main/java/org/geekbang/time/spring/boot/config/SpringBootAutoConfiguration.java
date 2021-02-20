package org.geekbang.time.spring.boot.config;

import java.util.ArrayList;
import java.util.List;

import org.geekbang.time.spring.boot.bean.Klass;
import org.geekbang.time.spring.boot.bean.School;
import org.geekbang.time.spring.boot.bean.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootAutoConfiguration {

	@Bean(name="student100")
	public Student getStudent100() {
		return new Student(100, "KK100");
	}
	
	@Bean(name="student123")
	public Student getStudent123() {
		return new Student(123, "KK123");
	}
	
	@Bean(name="class1")
	public Klass getKlass() {
		List<Student> stuList = new ArrayList<>();
		stuList.add(getStudent100());
		stuList.add(getStudent123());
		
		Klass k1 = new Klass();
		k1.setStudents(stuList);
		return k1;
	}
	
	@Bean
	public School getSchool() {
		return new School();
	}
}
