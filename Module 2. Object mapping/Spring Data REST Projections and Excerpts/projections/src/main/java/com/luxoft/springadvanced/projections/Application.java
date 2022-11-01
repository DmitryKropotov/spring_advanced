package com.luxoft.springadvanced.projections;

import com.luxoft.springadvanced.projections.Customer.Gender;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	@Autowired
	CustomerRepository customers;

	@PostConstruct
	public void init() {

		customers.save(new Customer("John", "Smith", Gender.MALE,
				new Address("3 Flowers Streets", "12345", "Boston", "MA")));

	}

	public static void main(String... args) {
		SpringApplication.run(Application.class, args);
	}


}
