package com.luxoft.springadvanced.springrest;

import com.luxoft.springadvanced.springrest.beans.FlightBuilder;
import com.luxoft.springadvanced.springrest.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.Map;

@SpringBootApplication
@Import(FlightBuilder.class)
public class Application {

    @Autowired
    private Flight flight;

    @Autowired
    private Map<String, Country> countriesMap;

    //http://localhost:8081/actuator/ - metrics
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner configureRepository(CountryRepository countryRepository,
                                          PassengerRepository passengerRepository) {
        return args -> {

            for (Country country : countriesMap.values()) {
                countryRepository.save(country);
            }

            for (Passenger passenger : flight.getPassengers()) {
                passengerRepository.save(passenger);
            }
        };
    }

}
