package com.luxoft.springadvanced.transactions;

import com.luxoft.springadvanced.transactions.data.repositories.BookDao;
import com.luxoft.springadvanced.transactions.orm.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.transaction.annotation.Isolation.*;

@SpringBootTest
public class IsolationLevelsTest {
    @Autowired
    private BookDao bookDao;

    @PersistenceContext
    EntityManager entityManager;

    // try with READ_COMMITTED (or with no isolation set) and REPEATABLE_READ
    @Test
    @Transactional(isolation = REPEATABLE_READ)
    public void unrepeatableRead() {
        System.out.println(bookDao.findAll().get(0).getId());

        Book book = bookDao.findById(419).get();
        System.out.println(book.getTitle());

        // set breakpoint here, then run updateBook1(), and change book title in this method
        Book book2 = bookDao.findById(419).get();
        System.out.println(book.getTitle());

        entityManager.refresh(book);
        System.out.println(book.getTitle());
    }

    // try with READ_COMMITTED (or with no isolation set) and SERIALIZABLE
    // Note: In HSQLDB REPEATABLE_READ and SERIALIZABLE is the same (uses SNAPSHOT isolation)
    @Test
    @Transactional(isolation = SERIALIZABLE)
    public void phantomRead() {
        List<Book> booksList1 = bookDao.findAll();
        System.out.println(booksList1.size());

        // set breakpoint here, run addBook()
        List<Book> booksList2 = bookDao.findAll();
        System.out.println(booksList2.size());
    }

    @Test
    public void addBook() {
        bookDao.save(new Book("Java", Date.valueOf(LocalDate.of(2015, 5, 1))));
    }

    @Test
    public void updateBook1() {
        Book book = bookDao.findById(419).get();
        book.setTitle("Java 11");
        bookDao.save(book);
    }

}
