package com.luxoft.springadvanced.spring;

import org.junit.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static com.luxoft.springadvanced.spring.PersonUtil.getExpectedPerson;
import static org.junit.Assert.assertEquals;

public class SimpleAppTest {

    private static final String APPLICATION_CONTEXT_XML_FILE_NAME =
            "classpath:application-context.xml";

    private ClassPathXmlApplicationContext context;

    private Person expectedPerson;

    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext(
                APPLICATION_CONTEXT_XML_FILE_NAME);
        expectedPerson = getExpectedPerson();
    }

    @Test
    public void testInitPassenger() {
        Person person = (Person) context.getBean("person");
        assertEquals(expectedPerson, person);
        System.out.println(person);
    }


}
