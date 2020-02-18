package be.task;

import be.task.spring_jdbc.Quote;

public interface QuoteProcessor {

    void put(Quote quote) throws InterruptedException;
}
