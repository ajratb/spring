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

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void writeLogInNewTransaction(String message) {
		logDao.save(new Log(message));
	}


	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void addSeparateLogsNotSupported() {
		logDao.save(new Log("check from not supported 1"));
//		if (true) throw new RuntimeException();
//		logDao.save(new Log("check from not supported 4"));
	}



	@Transactional
	public void addSeparateLogsSupportsWithTransaction() {
		addSeparateLogsSupports();
		//throw new RuntimeException();
	}

	/*
	 * If it is used separately then one record will be added to database
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public void addSeparateLogsSupports() {
		logDao.save(new Log("check from supports 1"));
		throw new RuntimeException();
//		if (true) throw new RuntimeException();
//		logDao.save(new Log("check from supports 2"));
	}

	@Transactional(propagation=Propagation.NEVER)
	public int showLogs() {
		System.out.println("Current log:");
		List<Log> logs = logDao.findAll();
		logs.forEach(System.out::println);
		return logs.size();
	}
}
