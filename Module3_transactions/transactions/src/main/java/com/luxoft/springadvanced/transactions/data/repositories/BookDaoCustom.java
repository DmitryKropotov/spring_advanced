package com.luxoft.springadvanced.transactions.data.repositories;

import java.sql.Date;

public interface BookDaoCustom {

	public void setPublishingHouse(String houseName);

	void addBook(String title, Date dateRelease);

	void checkTitleDuplicate(String title);

	public void addLogs();

	void showLogs();

	void addBookNoRollback(String title, Date dateRelease);

}
