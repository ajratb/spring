package wts.spring.jdbc.booking;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("wts.spring.jdbc.booking")
//@EnableTransactionManagement
@PropertySource("classpath:postgres-dev.properties")
public class JdbcConfig {

    @Autowired
    Environment env;

    private final String URL = "url";
    private final String USER = "dbuser";
    private final String DRIVER = "driver";
    private final String PASSWORD = "dbpassword";

    

//    @Bean
//    public DataSource pgDataSource() {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setDriverClassName(env.getProperty(DRIVER));
//
//        dataSource.setJdbcUrl(env.getProperty(URL));
//        //"jdbc:postgresql://localhost:5432/devdb?loggerLevel=TRACE");//&loggerFile=pgjdbc.log");
//
//        dataSource.setUsername(env.getProperty(USER));//"dev"
//        //CHECK IF IT DEPENDS ON pg_hba.conf 
//        dataSource.setPassword(env.getProperty(PASSWORD));
//        return dataSource;
//    }
//
//
    @Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//		log.info("Creating tables");
        jdbcTemplate.execute("drop table BOOKINGS if exists");
        jdbcTemplate.execute("create table BOOKINGS("
                + "ID serial, FIRST_NAME varchar(5) NOT NULL)");
        return jdbcTemplate;
    }
//
//    @Bean
//    BookingService bookingService(JdbcTemplate jdbc) {
//        return new BookingService(jdbc);
//    }
//    
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                //                .addScript("classpath:jdbc/test-data.sql")
                .build();
    }
//	@Bean
//	DataSource dataSource() {
//		return new SimpleDriverDataSource() {
//			{
//				setDriverClass(org.h2.Driver.class);
//				setUsername("sa");
//				setUrl("jdbc:h2:mem");
//				setPassword("");
//			}
//		};
//	}
}
