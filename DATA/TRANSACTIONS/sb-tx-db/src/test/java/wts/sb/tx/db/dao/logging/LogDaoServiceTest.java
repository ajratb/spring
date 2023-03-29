package wts.sb.tx.db.dao.logging;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Consumer;
import java.util.stream.Stream;

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

    @ParameterizedTest
    @MethodSource("provideAddLogsConsumer")
    public void testAddSeparateLogsWithinTx(Consumer<LogDaoService> lds) {

        assertThrows(
                RuntimeException.class, ()-> lds.accept(logDaoService));
        Assertions.assertThat(logDaoService.showLogs()).isZero();
    }

    private static Stream<Consumer<LogDaoService>> provideAddLogsConsumer() {
        return Stream.of(
                LogDaoService::addSeparateLogsWithNewTxWithinTx,
                LogDaoService::addSeparateLogsWithoutNewTxWithinTx
        );
    }

    @Test
    public void testAddSeparateLogsWithoutNewTx() {
        // executing without transaction:
        // addSeparateLogsSupports is working with no transaction
        assertThrows(
                RuntimeException.class,
                logDaoService::addSeparateLogsWithoutNewTx);
        // no transaction - first record is added in the log even after exception
        Assertions.assertThat(logDaoService.showLogs()).isEqualTo(1);
    }

    @Test
    public void testAddSeparateLogsWithNewTx() {
        // executing without transaction:
        // addSeparateLogsSupports is working with no transaction
        assertThrows(
                RuntimeException.class,
                logDaoService::addSeparateLogsWithNewTx);
        // no transaction - first record is added in the log even after exception
        Assertions.assertThat(logDaoService.showLogs()).isZero();
    }
}