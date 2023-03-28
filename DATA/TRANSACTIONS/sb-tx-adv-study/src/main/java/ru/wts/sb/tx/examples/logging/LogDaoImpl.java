package ru.wts.sb.tx.examples.logging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings({"ControlFlowStatementWithoutBraces", "ConstantValue"})
@Component
@RequiredArgsConstructor
public class LogDaoImpl implements LogDaoCustom {

	private final LogDao logDao;

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void log(String message) {
		logDao.save(new Log(message));
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void addSeparateLogsNotSupported() {
		logDao.save(new Log("check from not supported 1"));
		if (true) throw new RuntimeException();
		logDao.save(new Log("check from not supported 4"));
	}

	@Override
	@Transactional
	public void addSeparateLogsSupportsWithTransaction() {
		logDao.addSeparateLogsSupports();
		throw new RuntimeException();
	}


	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public void addSeparateLogsSupports() {
		logDao.save(new Log("check from supports 1"));
		if (true) throw new RuntimeException();
		logDao.save(new Log("check from supports 2"));
	}

	@Override
	@Transactional(propagation=Propagation.NEVER)
	public void showLogs() {
		System.out.println("Current log:");
		logDao.findAll().forEach(System.out::println);
	}

}
