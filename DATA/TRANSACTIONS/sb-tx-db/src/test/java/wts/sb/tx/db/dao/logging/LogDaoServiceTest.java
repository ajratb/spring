package wts.sb.tx.db.dao.logging;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class LogDaoServiceTest {

    @Autowired
    private LogDao logDao;
    @Autowired
    private LogDaoService logDaoService;

    @BeforeEach
    void setUp() {
        logDao.deleteAll();
    }

    @Test
    public void testAddSeparateLogsSupportsWithFailedTransaction() {

        assertThrows(
                RuntimeException.class,
                logDaoService::addSeparateLogsSupportsWithTransaction);
        Assertions.assertThat(logDaoService.showLogs()).isZero();
    }

    @Test
    public void testAddSeparateLogsSupports() {
        // executing without transaction:
        // addSeparateLogsSupports is working with no transaction
        assertThrows(
                RuntimeException.class,
                logDaoService::addSeparateLogsSupports);
        // no transaction - first record is added in the log even after exception
        Assertions.assertThat(logDaoService.showLogs()).isEqualTo(1);
    }
}