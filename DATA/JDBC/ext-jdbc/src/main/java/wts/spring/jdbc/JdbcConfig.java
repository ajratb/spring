package wts.spring.jdbc;

import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan("wts.spring.jdbc")
@PropertySource("classpath:postgres-dev.properties")
//@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class JdbcConfig {

	@Autowired
	Environment env;

	private final String URL = "url";
	private final String USER = "dbuser";
	private final String DRIVER = "driver";
	private final String PASSWORD = "dbpassword";

	private final String MAX_POOL = "max-pool-size";
	private final String MIN_IDLE = "min-idle";

	@Primary
	@Bean
	public DataSource pgDataSource() {

		int maxPool = getIntFromProp(MAX_POOL, 1);
		int minIdle = getIntFromProp(MIN_IDLE, -1);

		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName(env.getProperty(DRIVER));
		dataSource.setJdbcUrl(env.getProperty(URL));
		// "jdbc:postgresql://localhost:5432/devdb?loggerLevel=TRACE");//&loggerFile=pgjdbc.log");
		dataSource.setUsername(env.getProperty(USER));// "dev"
		// CHECK IF IT DEPENDS ON pg_hba.conf
		dataSource.setPassword(env.getProperty(PASSWORD));
		dataSource.setMaximumPoolSize(maxPool);
		dataSource.setMinimumIdle(minIdle);
		// !!!!!!!!!! ONLY TRANSACTIONAL METHOD WILL BE COMMIT !!!!!!!!!!!!!
		dataSource.setAutoCommit(false);
		// =======================================
		return dataSource;
	}

	private int getIntFromProp(String val, int initial) {
		String restored = Optional.ofNullable(env.getProperty(val)).orElse(String.valueOf(initial));
		return Integer.valueOf(restored);
	}

//    @Primary
	@Bean
	public DataSource h2DataSource() {
//		EmbeddedDatabase embeddedSource = 
		EmbeddedDatabaseBuilder sourceBuilder = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2);
		sourceBuilder
//		.generateUniqueName(true)
//		.setScriptEncoding("UTF-8")
//		.ignoreFailedDrops(true)
//		.addScript("classpath:jdbc/schema.sql")
//		.addScript("classpath:jdbc/test-data.sql").build();
				.addScript("db.sql");
//		 .addScripts("user_data.sql", "country_data.sql").build();
		return sourceBuilder.build();
	}

//	@Autowired
//	DataSource ds;

	/**
	 * You can inject dataSource using @Autowired or just invoking the method.
	 * 
	 * In case when datasource autocommit is set 'false' - 
	 * The modification methods will have no result if they are not @Transactional
	 * 
	 * @return
	 */
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(pgDataSource());// ds);
	}
}
