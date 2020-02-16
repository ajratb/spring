package wts.spring.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan("wts.spring.jdbc")
@PropertySource("classpath:db.properties")
public class JdbcConfig {

    @Autowired
    Environment environment;

    private final String URL = "url";
    private final String USER = "dbuser";
    private final String DRIVER = "driver";
    private final String PASSWORD = "dbpassword";

    @Bean
    public DataSource h2DataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(environment.getProperty(DRIVER));
        dataSource.setJdbcUrl(environment.getProperty(URL));
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
