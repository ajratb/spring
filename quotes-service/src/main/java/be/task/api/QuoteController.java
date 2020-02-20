package be.task.api;

import be.task.QuoteProcessor;
import be.task.dao.Quote;
import be.task.dao.QuoteDao;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ayrat
 */
@RestController
public class QuoteController {

    @Autowired
    QuoteProcessor processor;

    @Autowired
    QuoteDao dao;

    @PostMapping("/quotes")
    void putQuote(@RequestBody Quote quote) throws InterruptedException {

        processor.put(quote);
    }

    @GetMapping("/elvls/{isin}")
    Double getElvl(@PathVariable String isin) {

        return dao.getElvl(isin);
    }

    @GetMapping("/elvls")
    Map<String, Double> getElvl() {

        return dao.getIsinToElvlMap();
    }

    @GetMapping("/quotes")
    List<Quote> getAll() {

        return dao.getAll();
    }
}
