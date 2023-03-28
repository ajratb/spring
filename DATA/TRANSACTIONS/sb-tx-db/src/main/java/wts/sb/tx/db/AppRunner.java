package wts.sb.tx.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppRunner implements CommandLineRunner {

    @SuppressWarnings("RedundantThrows")
    @Override
    public void run(String... args) throws Exception {
        log.info("Hello");
    }
}
