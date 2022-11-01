package com.luxoft.springadvanced;

import com.luxoft.springadvanced.listeners.DatabaseOperationsListener;
import com.luxoft.springadvanced.database.ConnectionManager;
import com.luxoft.springadvanced.database.PersonDao;
import com.luxoft.springadvanced.database.PersonDaoImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@TestExecutionListeners(value = {
        DatabaseOperationsListener.class
})
public class PersonTest {

    private static Connection connection;
    private PersonDao personDao;

    @BeforeAll
    static void beforeAll() {
        connection = ConnectionManager.getConnection();
    }

    @BeforeEach
    public void beforeEach() {
        this.personDao = new PersonDaoImpl(connection);
    }

    @Test
    void testInsertPerson() {
        Person person = new Person("123-456-789", "John Smith");
        personDao.insert(person);
        assertEquals("John Smith", personDao.getById("123-456-789").getName());
    }

    @Test
    void testUpdatePerson() {
        Person person = new Person("123-789-456", "John Wilson");
        personDao.insert(person);
        personDao.update("123-789-456", "Michael Wilson");
        assertEquals("Michael Wilson", personDao.getById("123-789-456").getName());
    }

    @Test
    void testDeletePerson() {
        Person person = new Person("456-789-123", "Michael Johnson");
        personDao.insert(person);
        personDao.delete(person);
        assertNull(personDao.getById("456-789-123"));
    }

}
