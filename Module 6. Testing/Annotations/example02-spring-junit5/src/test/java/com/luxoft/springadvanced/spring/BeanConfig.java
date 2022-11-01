package com.luxoft.springadvanced.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
	
	@Bean
	public Person person() {
		Country country = new Country("USA", "US");
		Person person = new Person("John Smith");
		person.setCountry(country);
		return person;
	}

}
