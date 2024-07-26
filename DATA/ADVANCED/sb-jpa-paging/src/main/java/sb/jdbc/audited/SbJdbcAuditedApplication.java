package sb.jdbc.audited;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class SbJdbcAuditedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbJdbcAuditedApplication.class, args);
    }
}
