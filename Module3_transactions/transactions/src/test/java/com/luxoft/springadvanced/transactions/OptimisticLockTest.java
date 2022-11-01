package com.luxoft.springadvanced.transactions;

import com.luxoft.springadvanced.transactions.data.repositories.BookDao;
import com.luxoft.springadvanced.transactions.orm.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;


@SpringBootTest
@Rollback(value = false)
public class OptimisticLockTest {

    @Autowired
    BookDao bookDao;

    @Test
    @Transactional
    public void addBook() {
        Book book = new Book("Java");
        bookDao.save(book);
    }

    @Test
    @Transactional
    public void resetBook() {
//        System.out.println(bookDao.findAll().size());
//        System.out.println(bookDao.findAll().get(0).getId());
        Book book1 = bookDao.findById(419).get();
        book1.setTitle("Java");
        bookDao.save(book1);
    }

    @Test
    @Transactional
    public void readBook() {
        Book book1 = bookDao.findById(419).get();
        System.out.println("*******************************");
        System.out.println(book1.getTitle());
        System.out.println("*******************************");
    }

    @PersistenceContext
    EntityManager entityManager;

    // Optimistic transaction demo
    @Test
    @Transactional
    public void updateBook() {
        for (Book book: bookDao.findAll()) {
            System.out.println(book.getId());
        }
        Book book1 = bookDao.findById(419).get(); // version = 1
        System.out.println("****************");
        // uncomment for pessimistic lock
        //entityManager.lock(book1, LockModeType.PESSIMISTIC_WRITE);
        book1.setTitle(book1.getTitle()+"!");
        // to check optimistic locking, set breakpoint here and run updateBook2()
        bookDao.save(book1);
        readBook();
    } // version == 1 ? save : rollback

    @Test
    @Transactional
    public void updateBook2() {
        Book book1 = bookDao.findById(419).get(); // version = 1
        book1.setTitle(book1.getTitle()+"?");
        bookDao.save(book1); // version == 1
        readBook();
    } // version++

}
