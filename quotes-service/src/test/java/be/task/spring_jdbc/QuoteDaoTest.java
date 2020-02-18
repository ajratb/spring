package be.task.spring_jdbc;

import java.util.List;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuoteDaoTest {

    @Autowired
    private QuoteDao dao;

    @Test
    public void testQuoteToArray() {
        Object[] data = ((QuoteDaoImpl) dao).quoteToArray(new Quote("TestuoteToArray", 12, 33));
        assertThat(data).hasSize(4).containsExactly("TestuoteToArray", 12f, 33f, -1f);
    }

    @Test
    public void testFunQuotesToArray() {
        List<Quote> quotes = TestDataProvider.getQuotes();
        List<Object[]> data = ((QuoteDaoImpl) dao).funQuotesToArrays().apply(quotes);
        assertThat(data).hasSize(2);
        assertThat(data.get(0)).hasSize(4).containsExactly("1234567890ab", 4.4f, 5.5f, -1f);
        assertThat(data.get(1)).hasSize(4).containsExactly("ba0987654321", 54f, 123f, -1f);
    }

}
