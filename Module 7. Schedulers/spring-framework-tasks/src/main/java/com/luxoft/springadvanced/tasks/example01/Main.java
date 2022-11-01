package com.luxoft.springadvanced.tasks.example01;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("example01/application-context.xml");
	}
}
