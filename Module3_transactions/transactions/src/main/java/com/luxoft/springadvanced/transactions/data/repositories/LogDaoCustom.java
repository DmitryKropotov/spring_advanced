package com.luxoft.springadvanced.transactions.data.repositories;

public interface LogDaoCustom {

	public void log(String message);

	void showLogs();

	void showLogsInTransaction();

	void addSeparateLogsNotSupported();

	void addSeparateLogsSupports();
}
