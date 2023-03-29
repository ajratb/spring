package wts.sb.tx.db.dao.logging;

import org.springframework.data.jpa.repository.JpaRepository;
import wts.sb.tx.db.entities.Log;

public interface LogDao extends JpaRepository<Log, Integer> { }
