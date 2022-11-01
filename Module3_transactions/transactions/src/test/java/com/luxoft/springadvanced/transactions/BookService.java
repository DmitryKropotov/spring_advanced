package com.luxoft.springadvanced.transactions;

import com.luxoft.springadvanced.transactions.data.repositories.BookDao;
import com.luxoft.springadvanced.transactions.data.repositories.LogDao;
import com.luxoft.springadvanced.transactions.orm.model.Book;
import com.luxoft.springadvanced.transactions.orm.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    @Autowired
    BookDao bookDao;
    @Autowired
    BookService bookService;
    @Autowired
    LogDao logDao;

    // Demonstration: addBookAndLog() wouldn't be working as transactional here
    // if we not using self injection
    public void addBookAndLogInTransaction() {
        System.out.println("============ TEST SAME BEAN CALL =========");
        printBooksAndLogsAmount();
        try {
            // we call method of the same class - it will not be transactional
            addBookAndLog();
            // therefore, it adds book and doesn't add log
        } catch(Exception e) {}
        printBooksAndLogsAmount();
        // here we see that book is added, and log is not added

        System.out.println("============= TEST SELF INJECTED BEAN CALL ==============");
        printBooksAndLogsAmount();
        try {
            // use self injection - now we have transaction
            bookService.addBookAndLog();
            // therefore, it rollback adding book and log
        } catch(Exception e) {}
        printBooksAndLogsAmount();
        // here we can see that DB has not changed because
        // everything was rolled back
    }

    public void printBooksAndLogsAmount() {
        System.out.println("********** Books amount: "+ bookDao.findAll().size());
        System.out.println("********** Logs amount: "+ logDao.findAll().size());
    }

    @Transactional
    // if actually transactional, should rollback adding book and log (because of exception)
    public void addBookAndLog() {
        Book book = new Book("Java");
        bookDao.save(book);
        System.out.println("********** Book added **********");
        // add book
        if (true) throw new RuntimeException();
        // add log
        Log log = new Log("new message");
        logDao.save(log);
        System.out.println("********** Log added **********");
    }

    public void printBooksAmount() {
        System.out.println("********** Books amount: "+ bookDao.findAll().size());
    }


    class SomeException extends RuntimeException {}

    @Transactional(rollbackFor = SomeException.class)
    public void transactionWithException() {
        throw new SomeException();
    }

    @Transactional(rollbackFor = SomeException.class)
    public void transactionWithNoException() {
        try {
            throw new SomeException();
        } catch (SomeException e) {}
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addBookRequiresNew(String title) {
        bookDao.save(new Book(title));
    }

    @Transactional(propagation = Propagation.NESTED)
    public void addBookNested(String title) {
        bookDao.save(new Book(title));
    }

}
