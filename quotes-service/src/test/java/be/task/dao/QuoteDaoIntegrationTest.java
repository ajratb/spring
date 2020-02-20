package be.task.dao;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuoteDaoIntegrationTest {

    @Autowired
    QuoteDaoTestProxy daoProxy;

    @Test
    public void isDaoProxyAlive() {
        int count = daoProxy.getRowsCount();
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void testSaveAll() {

        int before = daoProxy.getRowsCount();

        List<Quote> quotes = TestDataProvider.getQuotes();
        before += quotes.size();
        daoProxy.saveAll(quotes);

        int after = daoProxy.getRowsCount();
        assertThat(before).isEqualTo(after);
    }

    @Test
    public void testGetELevel() {

        double elvl = daoProxy.getELevel("test12312355");
        assertThat(elvl).isEqualTo(2442347.4565);
    }
}
