package com.luxoft.springadvanced.spring;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration//("classpath:application-context.xml")
public class SpringAppTest {

    @Autowired
    private Person person;
    private Person expectedPerson;


    @Before
    public void setUp() {
        expectedPerson = PersonUtil.getExpectedPerson();
    }

    @Test
    public void testInitPassenger() {
        assertEquals(expectedPerson, person);
        System.out.println(person);
    }

}
