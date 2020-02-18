package be.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuoteProcessorImplTest {
    
    @Autowired
    QuoteProcessorImpl proc;
    
    @Test
    void work(){
//        proc.doWork();
    }
    
}
