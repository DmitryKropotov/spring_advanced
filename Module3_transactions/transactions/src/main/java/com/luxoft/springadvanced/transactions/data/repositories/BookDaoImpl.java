package com.luxoft.springadvanced.transactions.data.repositories;

import java.sql.Date;
import java.util.List;

import com.luxoft.springadvanced.transactions.orm.model.Book;
import com.luxoft.springadvanced.transactions.orm.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.luxoft.springadvanced.transactions.orm.dao.DuplicateBookTitleException;

public class BookDaoImpl implements BookDaoCustom {
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private LogDao log;
	
	@Transactional
	@Override
	public void setPublishingHouse(String publishingHouse) {
		List<Book> all = bookDao.findAll();
		for (Book b: all) {
			b.setPublishingHouse(publishingHouse);
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public void checkTitleDuplicate(String title) {
		if (bookDao.findAll().stream().map(b->b.getTitle()).filter(t->t.equals(title)).count()>0) {
			throw new DuplicateBookTitleException("book with title "+title+" already exists");
		}
	}
	
	@Override
	@Transactional
	public void addBook(String title, Date dateRelease) {
		log.log("adding book with name "+title);
		checkTitleDuplicate(title);
		bookDao.save(new Book(title, dateRelease));
	}

	@Override
	@Transactional(noRollbackFor=DuplicateBookTitleException.class)
	public void addBookNoRollback(String title, Date dateRelease) {
		log.save(new Log("adding log in method with no rollback for book "+title));
		checkTitleDuplicate(title);
		bookDao.save(new Book(title, dateRelease));
	}

	@Override
	@Transactional
	public void addLogs() {
		log.addSeparateLogsNotSupported();
	}
	
	@Override
	@Transactional
	public void showLogs() {
		log.showLogs();
	}
	
}
