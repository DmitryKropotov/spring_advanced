package com.luxoft.springadvanced.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=BeanConfig.class)
public class SpringAppJavaBeansTest {

    @Autowired
    private Person person;
    private Person expectedPerson;


    @BeforeEach
    public void setUp() {
        expectedPerson = PersonUtil.getExpectedPerson();
    }

    @Test
    public void testInitPassenger() {
        assertEquals(expectedPerson, person);
        System.out.println(person);
    }

}
