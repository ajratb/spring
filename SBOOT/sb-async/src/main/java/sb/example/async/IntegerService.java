package sb.example.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class IntegerService {

    @Async
    public CompletableFuture<Integer> getInteger(){
        log.info("Search integer");
        return CompletableFuture.completedFuture(1);
    }
}
