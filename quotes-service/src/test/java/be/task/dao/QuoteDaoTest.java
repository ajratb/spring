package be.task.dao;

import be.task.dao.QuoteDao;
import be.task.dao.QuoteDaoImpl;
import be.task.dao.Quote;
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
        
        Object[] data = ((QuoteDaoImpl) dao).quoteToArray(new Quote("TestuoteToArray", 12.22, 33.01));
        assertThat(data).hasSize(4).containsExactly("TestuoteToArray", 12.22, 33.01, 0.0);
    }

    @Test
    public void testFunQuotesToArray() {
        
        List<Quote> quotes = TestDataProvider.getQuotes();
        List<Object[]> data = ((QuoteDaoImpl) dao).funQuotesToArrays().apply(quotes);
        assertThat(data).hasSize(2);
        assertThat(data.get(0)).hasSize(4).containsExactly("1234567890ab", 4.4, 5.5, 0.0);
        assertThat(data.get(1)).hasSize(4).containsExactly("ba0987654321", 54.0, 123.0, 0.0);
    }

}
