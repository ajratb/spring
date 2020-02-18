package be.task;

import be.task.spring_jdbc.Quote;
import be.task.spring_jdbc.QuoteDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import static java.util.stream.Collectors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuoteProcessorImpl implements QuoteProcessor {

    private static final Logger log = LoggerFactory.getLogger(QuoteProcessorImpl.class);
    
    @Autowired
    QuoteDao dao;

    BlockingQueue<Quote> qQ = new LinkedBlockingQueue<>(10_000);

    @Override
    public void put(Quote q) throws InterruptedException {
        qQ.put(q);
    }

    public QuoteProcessorImpl() {
         this(Executors.newSingleThreadExecutor());
    }

    QuoteProcessorImpl(ExecutorService worker) {
//        worker.execute(this::doWork);
    }
    
    void doWork() {

        while (true) {
            try {
                work();
            } catch (InterruptedException threadEx) {
                log.error("!!! Thread was interrupted !!!", threadEx);
                return;
            }
        }
    }

    void work() throws InterruptedException {

        List<Quote> qList = new ArrayList<>();        
        qQ.drainTo(qList);
     
        if (qList.isEmpty()) {
            return;
        }
        
        Map<String, List<Quote>> qMap = qList.stream()
                .collect(groupingBy(Quote::getIsin));

        for (String isin : qMap.keySet()) {

            List<Quote> isinList = qMap.get(isin);
            float elvl = dao.getELevel(isin);
            processIsinList(elvl, isinList);
            dao.saveAll(isinList);
        }
    }

    void processIsinList(float elvl, List<Quote> isinList) {
        
        for (Quote q : isinList) {

            if (q.getBid() == 0) {

                elvl = q.getAsk();

            } else if (elvl < 0) {//is absent

                elvl = q.getBid();

            } else if (q.getBid() > elvl) {

                elvl = q.getBid();

            } else if (q.getAsk() < elvl) {

                elvl = q.getAsk();

            }
            q.setElvl(elvl);
        }
    }
}
