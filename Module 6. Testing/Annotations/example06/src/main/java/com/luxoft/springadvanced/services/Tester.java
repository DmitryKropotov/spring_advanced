package com.luxoft.springadvanced.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("tester")
public class Tester implements Job {
	@Value("${description}")
	private String description;

	@Override
	public String getDescription() {
		return description;
	}
}
