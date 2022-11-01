package com.luxoft.springadvanced.orm.dao.dev;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@Transactional
//@ActiveProfiles("dev")
public class PersonDaoTransactionUnitRollbackTest extends PersonDaoTransactionUnitTest {

    /* Tests person table and changes the first records last name.
       The default rollback is true, but we change this one method to false.
    */
    @Test
    @Rollback(false)
    public void testQueryDatabase() throws SQLException {
        super.testQueryDatabase();
    }
    
    /* Tests that the size and first record match what is expected
       after the transaction.
    */
    @AfterTransaction
    public void afterTransaction() {
        testPerson(false, CHANGED_LAST_NAME);
    }
    
}
