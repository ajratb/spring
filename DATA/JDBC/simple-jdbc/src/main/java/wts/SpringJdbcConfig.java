package wts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan("wts")
public class SpringJdbcConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2).build();
//                .addScript("classpath:jdbc/schema.sql")
//                .addScript("classpath:jdbc/test-data.sql").build();
    }
    
//   @Bean
//    public DataSource pgDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/devdb");
//        dataSource.setUsername("d");
//        //CHECK IF IT DEPENDS ON pg_hba.conf or maybe because dev is superuser
//        dataSource.setPassword("s");
//        return dataSource;
//    }

}
