package wts.sb.tx.db;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import wts.sb.tx.db.dao.book.BookDaoCustom;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionEmulator {

    private final BookDaoCustom bookDao;

    @SuppressWarnings("unused")
    public void runTest() {
        runInTransaction();
    }

    @Transactional
    protected void runInTransaction() {
        log.info("Transaction open? : {}", TransactionSynchronizationManager.isActualTransactionActive());
        bookDao.checkTitleDuplicate("title");
        log.info("Transaction open? : {}", TransactionSynchronizationManager.isActualTransactionActive());
    }

}
