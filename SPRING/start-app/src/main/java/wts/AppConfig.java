package wts;

//import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("wts")
public class AppConfig {
    
    
    
    @Bean
    public MyService myService() {
        MyService srv = new MyService();
        return srv;
    }
    
    @Bean
    @Primary
//    @Qualifier("first")
    public Student firstStudent(){
        return new Student("Petya", 16);
    }
    
    @Bean
    @Qualifier("second")
//    @Resource(name="second")
    public Student secondStudent(){
        return new Student("Vasya", 24);
    }

}
