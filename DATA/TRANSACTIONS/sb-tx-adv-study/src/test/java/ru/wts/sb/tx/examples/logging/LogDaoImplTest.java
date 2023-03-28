package ru.wts.sb.tx.examples.logging;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LogDaoImplTest {

    @Autowired
    private LogDao logDao;

    @BeforeEach
    void setUp() {
        logDao.deleteAll();
    }

    @Test
    public void supports() {
        // executing without transaction:
        // addSeparateLogsSupports is working with no transaction
        try {
            //logDao.addSeparateLogsSupports();
            logDao.addSeparateLogsSupportsWithTransaction();
        } catch (Exception e) {}
        // no transaction - first record is added in the log even after exception
        logDao.showLogs();
    }
}