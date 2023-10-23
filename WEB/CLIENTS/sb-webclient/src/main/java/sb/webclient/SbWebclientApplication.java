package sb.webclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Slf4j
@SpringBootApplication
public class SbWebclientApplication implements CommandLineRunner {

    @Override
    public void run(String... args) {
        try {
            WebClient.create("https://ya.ru/34234").get().retrieve().bodyToMono(String.class).block();
        } catch (Exception ex) {
            log.info("=== EXCEPTION ===");
            log.info("Class: {}", ex.getClass());
            log.info("Message: {}", ex.getMessage());
        }
//        try {
//            WebClient.create("https://yaRRRR.ru/34234").get().retrieve().bodyToMono(String.class).block();
//        } catch (Exception ex) {
//            log.info("=== EXCEPTION ===");
//            log.info("Class: {}", ex.getClass());
//            log.info("Message: {}", ex.getMessage());
//        }

        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofSeconds(4));
        WebClient webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
//        try {
//            String result = webClient.get().uri("http://localhost:8080/creatives/sleep")
//                    .retrieve().bodyToMono(String.class).block();
//            log.info("Result is: {}", result);
//        } catch (Exception ex) {
//            log.info("=== EXCEPTION ===");
//            log.info("Class: {}", ex.getClass());
//            log.info("Message: {}", ex.getMessage());
//        }

        log.info("START");
        Mono<ResponseEntity<Void>> reponse = webClient.get()
                .uri("https://ord.ozon.ru/api/ord-api/content/media/bf426fb1463ec1e857a6d238a5b7099c68a7862d3e0c468726edf141a5496eb6")
                //.header("Content-Length", "1000")
                .retrieve().toBodilessEntity()
                .doOnSuccess(entity -> log.info("clientResponse.headers() = {}",entity.getHeaders()))
                .doOnSuccess(entity -> log.info("clientResponse.statusCode() = {}",entity.getStatusCode()));
//                .flatMap(clientResponse -> Mono.just("DONE!"));//clientResponse.bodyToMono(String.class));

        ResponseEntity<Void> block = reponse.block();
        log.info("DONE");
    }

    public static void main(String[] args) {
        SpringApplication.run(SbWebclientApplication.class, args);
    }

}
