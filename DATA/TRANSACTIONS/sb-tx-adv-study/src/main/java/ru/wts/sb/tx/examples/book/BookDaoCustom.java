package ru.wts.sb.tx.examples.book;

import java.sql.Date;

@SuppressWarnings({"unused", "UnnecessaryModifier"})
public interface BookDaoCustom {

	public void setPublishingHouse(String houseName);

	void addBook(String title, Date dateRelease);

	void checkTitleDuplicate(String title);

	public void addLogs();

	void showLogs();

	void addBookNoRollback(String title, Date dateRelease);

}
