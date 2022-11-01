package com.luxoft.springadvanced.spring;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=BeanConfig.class)
public class SpringAppJavaBeansTest {

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
