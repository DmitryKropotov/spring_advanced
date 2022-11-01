package com.luxoft.springadvanced.webflux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.luxoft.springadvanced.webflux.dao.PassengerRepository;
import com.luxoft.springadvanced.webflux.model.Passenger;
 
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
@Service
public class PassengerServiceImpl implements PassengerService {
     
    @Autowired
    PassengerRepository passengerRepository;
 
    public Mono<Passenger> create(Passenger e) {
        Mono<Passenger> passenger = passengerRepository.save(e);
        passenger.subscribe();
        return passenger;
    }
 
    public Mono<Passenger> findById(Integer id) {
        return passengerRepository.findById(id);
    }
 
    public Flux<Passenger> findByName(String name) {
        return passengerRepository.findByName(name);
    }
 
    public Flux<Passenger> findAll() {
        return passengerRepository.findAll();
    }
 
    public Mono<Passenger> update(Passenger e) {
        return passengerRepository.save(e);
    }
 
    public Mono<Void> delete(Integer id) {
        return passengerRepository.deleteById(id);
    }
 
}