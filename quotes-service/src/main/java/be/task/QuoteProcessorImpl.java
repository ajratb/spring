package be.task;

import be.task.api.ElvlNotFoundException;
import be.task.dao.Quote;
import be.task.dao.QuoteDao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
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

    BlockingQueue<Quote> qQ = new LinkedBlockingQueue<>(100_000);

    @Autowired
    QuoteProcessorImpl(ExecutorService worker) {

        worker.execute(this::doWork);
    }

    @Override
    public void put(Quote q) throws InterruptedException {

        qQ.put(q);
    }

    void doWork() {

        while (true) {
            try {

                work();

            } catch (InterruptedException threadEx) {

                log.error("!!! Thread was interrupted !!!", threadEx);
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

            if (isin.length() != 12) {
                continue;
            }

            double elvl = 0;

            try {

                elvl = dao.getElvl(isin);

            } catch (ElvlNotFoundException ex) {

                log.trace("there wasn't found any record for isin:{}", isin);

            } catch (Exception ex) {

                log.error("!!! exception when call dao method:{}:{} !!!",
                        ex.getClass(), ex.getMessage());
            }

            processIsinList(elvl, isinList);

            dao.saveAll(isinList);
        }
    }

    void processIsinList(double elvl, List<Quote> isinList) {

        Iterator<Quote> it = isinList.iterator();

        while (it.hasNext()) {

            Quote q = it.next();

            if (q.getBid() >= q.getAsk()) {

                it.remove();
                continue;
            }

            if (q.getBid() == 0) {

                elvl = q.getAsk();

            } else if (elvl == 0) {//is absent

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
