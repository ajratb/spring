package be.task.spring_jdbc;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuoteDaoTestProxy {
    
    @Autowired
    private QuoteDao dao;

    public int[] saveAll(List<Quote> quotes) {
        return dao.saveAll(quotes);
    }

    public float getELevel(String isin) {
        return dao.getELevel(isin);
    }

    int getRowsCount() {
        return ((QuoteDaoImpl)dao).getRowsCount();
    }
}
