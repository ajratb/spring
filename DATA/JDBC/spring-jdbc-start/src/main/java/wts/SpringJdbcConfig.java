package wts;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan("wts")
@PropertySource("classpath:postgres-dev.properties")
public class SpringJdbcConfig {
	
	@Autowired
	Environment env;

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScript("db.sql")
//              .addScript("classpath:jdbc/schema.sql")
//              .addScript("classpath:jdbc/test-data.sql").build();
				.build();
	}

//	private final String USER = "dbuser";
//	private final String PASSWORD = "dbpassword";

//	@Primary
//	@Bean
//	public DataSource pgDataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("org.postgresql.Driver");
//		dataSource.setUrl("jdbc:postgresql://localhost:5432/devdb");
//		dataSource.setUsername(env.getProperty(USER));// "dev"
//		// CHECK IF IT DEPENDS ON pg_hba.conf
//		dataSource.setPassword(env.getProperty(PASSWORD));
//		return dataSource;
//	}
}
