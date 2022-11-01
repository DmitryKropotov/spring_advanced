package com.luxoft.springadvanced.transactions;

import com.luxoft.springadvanced.transactions.data.repositories.BookDao;
import com.luxoft.springadvanced.transactions.orm.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Rollback(false)
public class LogicalTransactionTest {
    @Autowired
    BookDao bookDao;
    @Autowired BookService bookService;

    public void addBook() {
        Book book = new Book("New Book");
        bookDao.save(book);
    }

    /**
     * Every logical transaction can define its separate rollback rules.
     * Once the rollback rule triggered from logical transaction,
     * an UnexpectedRollbackException is thrown but the physical transaction
     * can still call commit because the physical transaction is independent
     * from the scope of the logical transaction.
     *
     * But when the physical transaction encounters UnexpectedRollbackException
     * (which is an unchecked RuntimeException), it triggers the rollback
     * of the entire physical transaction and the client will see that a rollback occurred.
     *
     * Source:
     * https://stackoverflow.com/questions/16731524/difference-between-physical-and-logical-transactions-in-spring
    */

    @Test
    @Transactional
    public void testLogicalTransaction() {
        printBooksAmount();
        addBook();
        try {
            bookService.transactionWithException();
            // inside transaction we get an exception,
            // and it is marking the transaction for rollback-only:
            // setRollbackOnly(true)
        } catch (BookService.SomeException e) {}
        System.out.println("****** Here we proceed...");
        printBooksAmount();
    } // here we get UnexpectedRollbackException
    // Transaction silently rolled back because it has been marked as rollback-only


    @Test
    @Transactional
    public void testLogicalTransaction2() {
        printBooksAmount();
        addBook();
        try {
            bookService.transactionWithNoException();
        } catch (BookService.SomeException e) {}
        System.out.println("****** Here we proceed...");
        printBooksAmount();
    }


    @Test
    public void printBooksAmount() {
        System.out.println("********** Books amount: "+ bookDao.findAll().size());
    }

}
