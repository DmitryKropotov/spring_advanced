package com.luxoft.springadvanced;

public class Person {

    private String identifier;
    private String name;

    public Person(String identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person " + getName() + " with identifier: " + getIdentifier();
    }
}
