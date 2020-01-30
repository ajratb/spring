package wts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
@ComponentScan("wts")
public class SpringJdbcConfig {

   @Bean
    public DataSource pgDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/devdb");
        dataSource.setUsername("dev");
        //CHECK IF IT DEPENDS ON pg_hba.conf 
//        dataSource.setPassword("str");
        return dataSource;
    }

}
