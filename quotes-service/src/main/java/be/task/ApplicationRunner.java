package be.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import be.task.dao.QuoteDao;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ApplicationRunner.class);
//
    @Autowired
    private QuoteProcessor proc;
    
    @Autowired
    private QuoteDao dao;

    @Override
    public void run(String... strings) throws Exception {

    }
}
