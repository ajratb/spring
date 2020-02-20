package be.task;

import be.task.dao.Quote;

public interface QuoteProcessor {

    void put(Quote quote) throws InterruptedException;
}
