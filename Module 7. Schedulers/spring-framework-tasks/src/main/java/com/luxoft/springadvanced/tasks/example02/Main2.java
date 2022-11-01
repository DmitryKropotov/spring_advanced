package com.luxoft.springadvanced.tasks.example02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {
	public static void main(String[] args) {
		new AnnotationConfigApplicationContext(BeanConfig.class);
	}
}
