package be.task;

import be.task.spring_jdbc.Quote;
import be.task.spring_jdbc.QuoteDaoImpl;
import static java.time.Duration.*;
import java.util.concurrent.ExecutorService;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class QuoteProcessorImplTest {
    
//    @MockBean
//    ExecutorService executor;
//    
//    @Autowired
//    QuoteDaoImpl dao;
//    
    @Autowired
    QuoteProcessorImpl qp;
//    
//    
//    
//    
//    
    @Test
    void testWorkMethod() throws InterruptedException{
//        
////        QuoteProcessorImpl qp = new QuoteProcessorImpl(executor);
        for(int i = 0; i < 10000; i++)
        qp.qQ.add(createDummyQuote());
//        qp.work();
      assertTimeout(ofMillis(1000), qp::work);
    }
//    
    private Quote createDummyQuote(){
        return new Quote("123456789000", 0f, 1f);
    }
    
}
