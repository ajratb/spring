package be.task;

import be.task.api.QuoteController;
import be.task.dao.QuoteDao;
import be.task.dao.QuoteDaoTestProxy;
import java.util.concurrent.ExecutorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTests {

    @Autowired
    QuoteDao dao;

    @Autowired
    QuoteProcessor qp;

    @Autowired
    QuoteDaoTestProxy daoProxy;

//    @MockBean
    @Autowired
    ExecutorService executor;

    @Autowired
    QuoteController api;

    @Test
    void contextLoads() {
        assertThat(dao).isNotNull();
        assertThat(qp).isNotNull();
        assertThat(daoProxy).isNotNull();
        assertThat(executor).isNotNull();
        assertThat(api).isNotNull();
    }
}
