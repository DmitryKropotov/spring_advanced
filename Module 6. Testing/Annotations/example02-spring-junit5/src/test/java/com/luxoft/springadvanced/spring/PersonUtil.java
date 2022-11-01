package com.luxoft.springadvanced.spring;

public class PersonUtil {

    public static Person getExpectedPerson() {
        Person person = new Person("John Smith");

        Country country = new Country("USA", "US");

        person.setCountry(country);

        return person;
    }
}
