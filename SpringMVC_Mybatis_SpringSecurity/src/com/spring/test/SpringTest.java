package com.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	 public static void main(String[] args) {
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("config/spring-common.xml");
		 Object userMapper = ctx.getBean("userMapper");
		 Object roleMapper = ctx.getBean("roleMapper");
		 Object dataMapper = ctx.getBean("dataMapper");
		 System.out.println(userMapper);
		 System.out.println(roleMapper);
	 }
}
