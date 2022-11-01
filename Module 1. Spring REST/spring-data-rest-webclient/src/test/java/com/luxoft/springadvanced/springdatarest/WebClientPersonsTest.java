package com.luxoft.springadvanced.springdatarest;

import com.luxoft.springadvanced.springdatarest.model.Country;
import com.luxoft.springadvanced.springdatarest.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebClientPersonsTest {

    @Test
    void testGetPerson() {
        WebClient webClient = WebClient.create("http://localhost:8081");
        ClientResponse clientResponse = webClient.get()
                .uri("/persons/1")
                .exchange()
                .block();
        ClientResponse.Headers headers = clientResponse.headers();

        Map<String, Object> responseMap = clientResponse.body(BodyExtractors.toMono(Map.class)).block();
        System.out.println(responseMap);

        assertAll(
                () -> assertEquals("Jack Vaughn", responseMap.get("name")),
                () -> assertEquals(false, responseMap.get("registered")),
                () -> assertEquals(89, headers.contentLength().getAsLong()),
                () -> assertEquals(MediaType.APPLICATION_JSON, headers.contentType().get()),
                () -> assertEquals(HttpStatus.OK, clientResponse.statusCode()));

    }

    @Test
    void testPostPerson() {
        WebClient webClient = WebClient.create("http://localhost:8081");
        Person person = new Person();
        person.setName("Michael Stephens");
        person.setCountry(new Country("Australia", "AU"));
        ClientResponse clientResponse = webClient.post()
                .uri("/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(person))
                .exchange()
                .block();

        assertEquals(HttpStatus.CREATED, clientResponse.statusCode());
    }

}
