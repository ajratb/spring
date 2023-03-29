package wts.sb.tx.db.dao.logging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wts.sb.tx.db.entities.Log;

import java.util.List;

@SuppressWarnings({"ControlFlowStatementWithoutBraces", "CommentedOutCode", "unused"})
@Component
@RequiredArgsConstructor
public class LogDaoService {

	private final LogDao logDao;

	@Transactional(propagation=Propagation.SUPPORTS)
	public void writeLogInNewTransaction(String message) {
		logDao.save(new Log(message));
	}


	@Transactional
	public void addSeparateLogsWithoutNewTxWithinTx() {
		addSeparateLogsWithoutNewTx();
		// no need:
//		throw new RuntimeException();
	}

	@Transactional
	public void addSeparateLogsWithNewTxWithinTx() {
		addSeparateLogsWithNewTx();
		// no need:
//		throw new RuntimeException();
	}

	/*
	 * For SUPPORTS, NOT_SUPPORTED and NEVER propagations
	 * If it is used separately then one record will be added to database
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public void addSeparateLogsWithoutNewTx() {
		logDao.save(new Log("check from supports 1"));
		throw new RuntimeException();
//		if (true) throw new RuntimeException();
//		logDao.save(new Log("check from supports 2"));
	}

	/*
	 * For MANDATORY, REQUIRES, REQUIRES_NEW and NESTED propagations
	 * No record will be added to database anyway!
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void addSeparateLogsWithNewTx() {
		logDao.save(new Log("check from supports 1"));
		throw new RuntimeException();
//		if (true) throw new RuntimeException();
//		logDao.save(new Log("check from supports 2"));
	}

//	@Transactional(propagation=Propagation.NOT_SUPPORTED)
//	public void addSeparateLogsNotSupported() {
//		logDao.save(new Log("check from not supported 1"));
//		throw new RuntimeException();
////		if (true) throw new RuntimeException();
////		logDao.save(new Log("check from not supported 4"));
//	}

	@Transactional(propagation=Propagation.NEVER)
	public int showLogs() {
		System.out.println("Current log:");
		List<Log> logs = logDao.findAll();
		logs.forEach(System.out::println);
		return logs.size();
	}
}
