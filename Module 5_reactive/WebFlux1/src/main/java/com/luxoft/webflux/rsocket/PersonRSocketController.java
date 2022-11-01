package com.luxoft.webflux.rsocket;

import com.luxoft.webflux.domain.Person;
import com.luxoft.webflux.repo.PersonRepository;
import com.luxoft.webflux.service.NameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class PersonRSocketController {
    private final PersonRepository personRepository;

    @Value("${spring.rsocket.server.port}")
    Long port;

    @Autowired
    public PersonRSocketController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @MessageMapping("findById") // request response
    Mono<Person> getPersonById(Long id) {
        if (port == 7000) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return personRepository.findById(1L)
                //.delayElement(port==7000?Duration.ofMillis(1000):Duration.ZERO)
                .doOnNext(person ->
                        System.out.println("Retrieved "+id+" on port "+port))
                .map(person -> new Person(
                        port,
                        id,
                        person.getName(),
                        person.getSurname()));
    }

    @MessageMapping("all") // request stream
    Flux<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @MessageMapping("add") // fire and forget
    Mono<Void> addPerson(Person person) {
        personRepository.save(person);
        return Mono.empty();
    }

    @MessageMapping("byId") // channel
    Flux<Person> getPersonByIds(Flux<Long> ids) {
        return ids.flatMap(personRepository::findById);
    }
}
