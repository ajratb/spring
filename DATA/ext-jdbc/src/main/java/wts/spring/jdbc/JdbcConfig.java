package wts.spring.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan("wts.spring.jdbc")
@PropertySource("classpath:postgres-dev.properties")
//@PropertySource("classpath:db.properties")
public class JdbcConfig {

    @Autowired
    Environment env;

    private final String URL = "url";
    private final String USER = "dbuser";
    private final String DRIVER = "driver";
    private final String PASSWORD = "dbpassword";

    @Primary
     @Bean
    public DataSource pgDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(env.getProperty(DRIVER));

        dataSource.setJdbcUrl(env.getProperty(URL));
        //"jdbc:postgresql://localhost:5432/devdb?loggerLevel=TRACE");//&loggerFile=pgjdbc.log");

        dataSource.setUsername(env.getProperty(USER));//"dev"
        //CHECK IF IT DEPENDS ON pg_hba.conf 
        dataSource.setPassword(env.getProperty(PASSWORD));
        return dataSource;
    }
    
    @Bean
    public DataSource h2DataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(env.getProperty(DRIVER));
        dataSource.setJdbcUrl(env.getProperty(URL));
//        dataSource.setUsername(environment.getProperty(USER));
//        dataSource.setPassword(environment.getProperty(PASSWORD));
        dataSource.setMaximumPoolSize(1000);
        return dataSource;
    }

//
//    @Bean
//    public DataSource pgDataSource() {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setDriverClassName(environment.getProperty(DRIVER));
//        
//        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/devdb");//?loggerLevel=TRACE&loggerFile=pgjdbc.log");
//        
//        dataSource.setUsername("dev");
//        //CHECK IF IT DEPENDS ON pg_hba.conf 
//        dataSource.setPassword(environment.getProperty(PASSWORD));
//        return dataSource;
//    }
//    @Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .addScript("classpath:jdbc/schema.sql")
//                .addScript("classpath:jdbc/test-data.sql").build();
//    }
}
