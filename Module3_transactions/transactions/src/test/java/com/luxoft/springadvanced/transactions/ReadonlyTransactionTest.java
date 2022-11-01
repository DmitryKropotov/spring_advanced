package com.luxoft.springadvanced.transactions;

import com.luxoft.springadvanced.transactions.data.repositories.BookDao;
import com.luxoft.springadvanced.transactions.data.repositories.LogDao;
import com.luxoft.springadvanced.transactions.orm.model.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Run these tests in debugger to see the generated SQL
 */
@SpringBootTest
@Rollback(value = false)
public class ReadonlyTransactionTest {
    @Autowired
    BookDao bookDao;
    @Autowired
    LogDao logDao;

    @Test
    @Transactional(readOnly = true)
    public void readOnlyTransaction() {
        bookDao.findAll();
        Log log = new Log("new message");
        logDao.save(log); // new log message will not be saved
        // insert will not be generated

        List<Log> logs = logDao.findAll();
        Log log1 = logs.get(0);
        log1.setMessage("updated message");
        // here the log1 will not be updated
    }

    @Test
    @Transactional(readOnly = false)
    public void notReadonlyTransaction() {
        bookDao.findAll();
        Log log = new Log("new message");
        logDao.save(log); // new log message will be saved

        List<Log> logs = logDao.findAll();
        Log log1 = logs.get(0);
        log1.setMessage("updated message");
        // here log1 will be updated (on transaction commit)
    }

}
