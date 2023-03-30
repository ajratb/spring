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
class LogDaoCustomTest {

    @Autowired
    private LogDao logDao;
    @Autowired
    private LogDaoCustom logDaoCustom;

    @BeforeEach
    void setUp() {
        logDao.deleteAll();
    }

    @ParameterizedTest
    @MethodSource("provideAddLogsConsumer")
    public void testAddSeparateLogsWithinTx(Consumer<LogDaoCustom> lds) {

        assertThrows(
                RuntimeException.class, ()-> lds.accept(logDaoCustom));
        Assertions.assertThat(logDaoCustom.showLogs()).isZero();
    }

    private static Stream<Consumer<LogDaoCustom>> provideAddLogsConsumer() {
        return Stream.of(
                LogDaoCustom::addSeparateLogsWithNewTxWithinTx,
                LogDaoCustom::addSeparateLogsWithoutNewTxWithinTx
        );
    }

    @Test
    public void testAddSeparateLogsWithoutNewTx() {
        // executing without transaction:
        // addSeparateLogsSupports is working with no transaction
        assertThrows(
                RuntimeException.class,
                logDaoCustom::addSeparateLogsWithoutNewTx);
        // no transaction - first record is added in the log even after exception
        Assertions.assertThat(logDaoCustom.showLogs()).isEqualTo(1);
    }

    @Test
    public void testAddSeparateLogsWithNewTx() {
        // executing without transaction:
        // addSeparateLogsSupports is working with no transaction
        assertThrows(
                RuntimeException.class,
                logDaoCustom::addSeparateLogsWithNewTx);
        // no transaction - first record is added in the log even after exception
        Assertions.assertThat(logDaoCustom.showLogs()).isZero();
    }
}