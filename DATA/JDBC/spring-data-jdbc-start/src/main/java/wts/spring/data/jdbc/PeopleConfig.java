package wts.spring.data.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
//import org.springframework.data.jdbc.repository.config.JdbcRepositoryConfigExtension;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("wts.spring.data.jdbc")
@EnableJdbcRepositories
@EnableTransactionManagement // is it needed. Can i perform transactions without it?
@PropertySource("classpath:db.properties")
public class PeopleConfig extends AbstractJdbcConfiguration{ // that's from documentation
	
	// JdbcConfiguration - is it old class?(from habr 2018)
	//?JdbcRepositoryConfigExtension { - why is it here

    @Autowired
    Environment environment;
    private final String URL = "url";
    private final String USER = "dbuser";
    private final String DRIVER = "driver";
    private final String PASSWORD = "dbpassword";

    @Bean
    NamedParameterJdbcOperations operations() {
        return new NamedParameterJdbcTemplate(pgDataSource());
    }

    @Bean
    PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(pgDataSource());
    }

    @Bean
    public DataSource pgDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty(DRIVER));
        dataSource.setUrl("jdbc:postgresql://localhost:5432/devdb");
        dataSource.setUsername("dev");
        //CHECK IF IT DEPENDS ON pg_hba.conf 
        dataSource.setPassword(environment.getProperty(PASSWORD));
        return dataSource;
    }

//    @Bean
//    DataSource dataSource(){
//        return new EmbeddedDatabaseBuilder()
//                .generateUniqueName(true)
//                .setType(EmbeddedDatabaseType.HSQL)
//                .addScript("create-customer-schema.sql")
//                .build();
//    }
}
