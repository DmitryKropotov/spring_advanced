package com.luxoft.springadvanced.transactions;

import com.luxoft.springadvanced.transactions.data.repositories.BookDao;
import com.luxoft.springadvanced.transactions.data.repositories.LogDao;
import com.luxoft.springadvanced.transactions.orm.dao.DuplicateBookTitleException;
import com.luxoft.springadvanced.transactions.orm.model.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;


/**
 * Illustrates various transaction propagation attributes.
 */
@SpringBootTest
//@Rollback(false)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionPropagationTest {

	@Autowired
	private BookDao bookDao;

	@Autowired
	private LogDao logDao;

	@BeforeEach
	public void clean() {
		bookDao.deleteAll();
		logDao.deleteAll();
	}

	@Transactional
	public void nonRepeatable() {

	}

	@Test
	public void notSupported() {
		// executing in transaction:
		// addLogs is starting transaction, but addSeparateLogsNotSupported() suspends it
		try {
			bookDao.addLogs();
		} catch (Exception e) {}
		
		// no transaction - first record is added in the log even after exception
		logDao.showLogs();		
	}

	// check if NOT_SUPPORTED is able to see changed outside transaction
	@Test
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void notSupportedSeeChanges() {
		printBooksAmount();
		printBooksAmount();
		suspendTransactionAndPrintBooksAmount();
	}

	// check if NOT_SUPPORTED is able to see changed outside transaction
	@Test
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void notSupportedSeeChanges2() {
		addBook();
		printBooksAmount();
		suspendTransactionAndPrintBooksAmount();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
	public void suspendTransactionAndPrintBooksAmount() {
		printBooksAmount();
	}

	@Test
	public void printBooksAmount() {
		System.out.println("********** Books amount: "+ bookDao.findAll().size());
	}

	@Test
	public void addBook() {
		Book book = new Book("New Book");
		bookDao.save(book);
	}
	
	@Test
	public void supports() {
		// executing without transaction:
		// addSeparateLogsSupports is working with no transaction
		try {
			logDao.addSeparateLogsSupports();
		} catch (Exception e) {}

		// no transaction - first record is added in the log even after exception
		logDao.showLogs();		
	}

	@Test
	public void mandatory() {
		// get exception because checkTitleDuplicate can be executed only in transaction
		try {
			bookDao.checkTitleDuplicate("Java");
		} catch(Exception e) {
			System.out.println("ERROR! "+e.getMessage());
		}
	}
	
	@Test
	public void never() {
		bookDao.addBook("Java1", Date.valueOf(LocalDate.of(2015, 5, 1)));
		// it's safe to call showLogs from no transaction
		logDao.showLogs();
		
		// but prohibited to execute from transaction
		try {
			bookDao.showLogs();
		} catch(Exception e) {
			System.out.println("ERROR! "+e.getMessage());
		}
	}
	
	@Test
	public void requiresNew() {
		// requires new - log message is persisted in the logs even after exception
		// because it was added in the separate transaction
		bookDao.addBook("Java17", Date.valueOf(LocalDate.of(2015, 5, 1)));
		bookDao.addBook("Spring", Date.valueOf(LocalDate.of(2016, 3, 1)));
		bookDao.addBook("Spring Data", Date.valueOf(LocalDate.of(2016, 1, 1)));
		
		try {
			bookDao.addBook("Spring", Date.valueOf(LocalDate.of(2016, 3, 1)));
		} catch (DuplicateBookTitleException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Logs: ");
		logDao.findAll().forEach(System.out::println);
		
		System.out.println("List of added books: ");
		bookDao.findAll().forEach(System.out::println);
	}

	@Test
	public void noRollback() {
		// no rollback - log message is persisted in the logs even after exception
		// because transaction was not rolled back
		bookDao.addBookNoRollback("Java", Date.valueOf(LocalDate.of(2015, 5, 1)));
		bookDao.addBookNoRollback("Spring", Date.valueOf(LocalDate.of(2016, 3, 1)));
		bookDao.addBookNoRollback("Spring Data", Date.valueOf(LocalDate.of(2016, 1, 1)));
		
		try {
			bookDao.addBookNoRollback("Spring", Date.valueOf(LocalDate.of(2016, 3, 1)));
		} catch (DuplicateBookTitleException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Logs: ");
		logDao.findAll().forEach(System.out::println);
		
		System.out.println("List of added books: ");
		bookDao.findAll().forEach(System.out::println);
	}

}
