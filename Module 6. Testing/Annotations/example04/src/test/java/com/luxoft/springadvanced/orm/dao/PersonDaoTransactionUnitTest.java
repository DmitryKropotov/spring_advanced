package com.luxoft.springadvanced.orm.dao;

import com.luxoft.springadvanced.orm.bean.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@Transactional
public class PersonDaoTransactionUnitTest extends AbstractTransactionalJUnit4SpringContextTests {

    private Logger logger = LoggerFactory.getLogger(PersonDaoTransactionUnitTest.class);

    protected static int SIZE = 2;
    protected static int ID = 1;
    protected static String FIRST_NAME = "Joe";
    protected static String LAST_NAME = "Smith";
    protected static String CHANGED_LAST_NAME = "Jackson";

    @Autowired
    protected PersonDao personDao;

    /* Tests that the size and first record match what is expected
       before the transaction.
    */
    @BeforeTransaction
    public void beforeTransaction() {
        testPerson(true, LAST_NAME);
    }

    /* Tests person table and changes the last name of the first record.
    */
    @Test
    public void testQueryDatabase() throws SQLException {
        Assertions.assertNotNull(personDao, () -> "Person list is null.");

        Collection<Person> persons = personDao.findPersons();

        Assertions.assertNotNull(persons, () -> "Person list is null.");
        assertEquals(SIZE, persons.size(), "Number of persons should be " + SIZE + ".");

        for (Person person : persons) {
            Assertions.assertNotNull(person, () -> "Person is null.");

            if (ID == person.getId()) {
                assertEquals(FIRST_NAME, person.getFirstName(), () -> "Person first name should be " + FIRST_NAME + ".");
                assertEquals(LAST_NAME, person.getLastName(), () -> "Person last name should be " + LAST_NAME + ".");

                person.setLastName(CHANGED_LAST_NAME);

                personDao.save(person);
            }
        }
    }

    /* Tests that the size and first record match what is expected
       after the transaction.
    */
    @AfterTransaction
    public void afterTransaction() {
        testPerson(false, LAST_NAME);
    }

    protected void testPerson(boolean beforeTransaction, String matchLastName) {
        List<Map<String, Object>> personsMap = jdbcTemplate.queryForList("SELECT * FROM PERSON");

        Assertions.assertNotNull(personsMap, () -> "Person list is null.");
        assertEquals(SIZE, personsMap.size(), () -> "Number of persons should be " + SIZE + ".");

        Map<String, Object> firstPerson = personsMap.get(0);

        logger.info((beforeTransaction ? "Before" : "After") + " transaction.  " + firstPerson.toString());

        int id = (int)firstPerson.get("ID");
        String firstName = (String)firstPerson.get("FIRST_NAME");
        String lastName = (String)firstPerson.get("LAST_NAME");

        if (id == ID) {
            assertEquals(FIRST_NAME, firstName, () -> "Person first name should be " + FIRST_NAME + ".");
            assertEquals(matchLastName, lastName, () -> "Person last name should be " + matchLastName + ".");
        }
    }

}
