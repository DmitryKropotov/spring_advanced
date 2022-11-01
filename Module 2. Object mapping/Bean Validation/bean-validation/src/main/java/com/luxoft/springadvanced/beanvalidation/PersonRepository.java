package com.luxoft.springadvanced.beanvalidation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
