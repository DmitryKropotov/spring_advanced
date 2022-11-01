package com.luxoft.springadvanced.transactions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Rollback(value = false)
public class SameBeanTransactionTest {
    @Autowired
    BookService bookService;

    @Test
    public void testSameBeanTransaction() {
        bookService.addBookAndLogInTransaction();
    }

}
