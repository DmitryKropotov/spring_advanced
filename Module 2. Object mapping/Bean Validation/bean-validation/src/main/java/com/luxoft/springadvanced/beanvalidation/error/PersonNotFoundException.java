package com.luxoft.springadvanced.beanvalidation.error;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(Long id) {
        super("Person id not found : " + id);
    }

}
