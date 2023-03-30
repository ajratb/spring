package wts.sb.tx.db.dao.versionedbook;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import wts.sb.tx.db.entities.VersionedBook;


@SuppressWarnings("unused")
public interface VersionedBookDao extends JpaRepository<VersionedBook, Integer> {

    @Lock(LockModeType.OPTIMISTIC)
    VersionedBook findWithOptimisticLockById(Integer id);

    @Lock(LockModeType.PESSIMISTIC_READ)
    VersionedBook findWithPessimisticReadLockById(Integer id);

    //@Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    VersionedBook findWithPessimisticWriteLockById(Integer id);

}
