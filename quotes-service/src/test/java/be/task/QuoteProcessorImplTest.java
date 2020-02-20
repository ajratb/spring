package be.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import be.task.dao.Quote;
import be.task.dao.QuoteDaoTestProxy;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;
import static java.time.Duration.*;
import static org.assertj.core.api.Assertions.*;
import static be.task.dao.TestDataProvider.*;

@SpringBootTest
public class QuoteProcessorImplTest {

    private static final Logger log = LoggerFactory.getLogger(ApplicationRunner.class);

    @MockBean
    ExecutorService worker;

    @Autowired
    QuoteDaoTestProxy daoProxy;

    @Autowired
    QuoteProcessorImpl qp;

    @Test
    void testWorkMethod() throws InterruptedException {

        int count = daoProxy.getRowsCount();
        System.out.println("\n========== rows count: " + count + " ==========\n");

        for (int i = 0; i < 100; i++) {
            qp.qQ.add(createDummyQuote());
        }

        for (int i = 0; i < 100; i++) {
            qp.qQ.addAll(getQuotes());
        }

        for (int i = 0; i < 10; i++) {
            qp.qQ.add(createQuoteWithShortIsin());
        }

        for (int i = 0; i < 10; i++) {
            qp.qQ.add(createQuoteWithLongIsin());
        }

        for (int i = 0; i < 10; i++) {
            qp.qQ.add(createQuoteWithBidBiggerThanAsk());
        }

        assertTimeout(ofMillis(1000), qp::work);

        int countAfter = daoProxy.getRowsCount();
        System.out.println("\n========== rows count: " + countAfter + " ==========\n");
    }

    @Test
    void testProcessIsinList() {

        Quote startQ = new Quote("123456789000", 10f, 10.1f);
//        startQ.setElvl(20);
        List<Quote> qList = new ArrayList<>();
        qList.add(startQ);
        assertThat(startQ.getElvl()).isEqualTo(0f);
        qp.processIsinList(10.2f, qList);
        assertThat(startQ.getElvl()).isEqualTo(10.1f);

        Quote invalid = createQuoteWithBidBiggerThanAsk();
        qList.add(invalid);
        qp.processIsinList(10, qList);
        assertThat(qList).doesNotContain(invalid).contains(startQ).hasSize(1);
    }
}
