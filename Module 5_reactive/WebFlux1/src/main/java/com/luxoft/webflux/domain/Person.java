package com.luxoft.webflux.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
//    @Transient
//    private Long port;

    public Person(Long port, Long id, String name, String surname) {
        this.id = id;
//        this.port = port;
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
