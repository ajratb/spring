package ru.wts.sb.tx.examples.logging;

@SuppressWarnings("UnnecessaryModifier")
public interface LogDaoCustom {

	public void log(String message);

	void showLogs();

	void addSeparateLogsNotSupported();

	@SuppressWarnings("unused")
	public void addSeparateLogsSupportsWithTransaction();

	void addSeparateLogsSupports();

}
