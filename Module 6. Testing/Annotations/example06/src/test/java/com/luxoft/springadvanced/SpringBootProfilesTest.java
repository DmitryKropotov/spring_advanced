package com.luxoft.springadvanced;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import com.luxoft.springadvanced.services.Job;

@ActiveProfiles({ "prod", "tester" })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringBootProfilesTest {
	@Autowired
	private Job job;
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void serviceTest() {
		String description = job.getDescription();
		assertEquals("I am a tester.", description);
	}

	@Test
	public void webAppTest() {
		String url = "http://localhost:8082/prod-profile/";
		String body = restTemplate.getForObject(url, String.class);
		assertEquals("I am a tester.", body);
	}
}