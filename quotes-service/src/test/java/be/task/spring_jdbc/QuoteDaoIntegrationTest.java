package be.task.spring_jdbc;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuoteDaoIntegrationTest {

   @Autowired
   QuoteDaoTestProxy dao;
   
    @Test
    public void isDaoAlive(){
        int count = dao.getRowsCount();
        assertThat(count).isEqualTo(1);
    }
    
    @Test
    public void testSaveAll(){
        
        List<Quote> quotes = TestDataProvider.getQuotes();
        dao.saveAll(quotes);
        
//        dao.s(quotesData);
    }
    
    @Test
    public void testGetELevel() {
        
        float elvl = dao.getELevel("test12312355");
        assertThat(elvl).isEqualTo(2442347.4565f);
        float elvl2 = dao.getELevel("test1231235");
    }
//        Quote quote = new Quote();
//        quote.setIsin("TestCorp");
//        quote.setBid(25.34f);
//        
//        quote.setAsk(32.43f);
//        boolean saved = dao.createQuote(quote);
//
//        assertThat(saved).isTrue();//.isNotNull();
//
//        List<Quote> persons = dao.getAll();
//        
//        assertThat(persons).hasSize(2);
        
//        saved.firstName = "Hans Albert";
//
//        personDao.save(saved);
//
//        Optional<People> reloaded = personDao.findById(saved.id);
//
//        assertThat(reloaded).isNotEmpty();
//
//        assertThat(reloaded.get().firstName).isEqualTo("Hans Albert");
//    }
}
