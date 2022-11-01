package com.luxoft.springadvanced.spring;

import static com.luxoft.springadvanced.spring.PersonUtil.getExpectedPerson;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration//("classpath:application-context.xml")
public class SpringAppTest {

    @Autowired
    private Person person;
    private Person expectedPerson;


    @BeforeEach
    public void setUp() {
        expectedPerson = getExpectedPerson();
    }

    @Test
    public void testInitPassenger() {
        assertEquals(expectedPerson, person);
        System.out.println(person);
    }

}
