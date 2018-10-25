package wts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

//import wts.MyService;

@Configuration

public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public MyService myService() {
        MyService client = new MyService();
        return client;
    }

}
