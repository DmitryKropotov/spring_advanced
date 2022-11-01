package com.luxoft.springadvanced.webflux;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import com.luxoft.springadvanced.webflux.controller.PassengerController;
import com.luxoft.springadvanced.webflux.service.PassengerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.luxoft.springadvanced.webflux.dao.PassengerRepository;
import com.luxoft.springadvanced.webflux.model.Passenger;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = PassengerController.class)
@Import(PassengerServiceImpl.class)
public class PassengerControllerTest
{
	@MockBean
	PassengerRepository repository;

	@Autowired
	private WebTestClient webClient;

	@Test
	void testCreatePassenger() {
		Passenger passenger = new Passenger();
		passenger.setId(1);
		passenger.setName("John Smith");
		passenger.setCoveredDistance(1000);

		Mockito.when(repository.save(passenger)).thenReturn(Mono.just(passenger));

		webClient.post()
			.uri("/create")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromValue(passenger))
			.exchange()
			.expectStatus().isCreated();

		Mockito.verify(repository, times(1)).save(passenger);
	}
	
	@Test
    void testGetPassengersByName() {
		Passenger passenger = new Passenger();
		passenger.setId(1);
		passenger.setName("John Smith");
		passenger.setCoveredDistance(1000);
		
		List<Passenger> list = new ArrayList<Passenger>();
		list.add(passenger);
		
		Flux<Passenger> passengerFlux = Flux.fromIterable(list);
		
        Mockito
            .when(repository.findByName("John Smith"))
            .thenReturn(passengerFlux);

        webClient.get().uri("/name/{name}", "John Smith")
        	.header(HttpHeaders.ACCEPT, "application/json")
	        .exchange()
	        .expectStatus().isOk()
	        .expectBodyList(Passenger.class);
        
        Mockito.verify(repository, times(1)).findByName("John Smith");
    }
	
}
