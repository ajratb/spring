package sb.data.multipledatasources.clickhouse;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Represents configuration for clickhouse data source.
 * Important! For JdbcRepos Configuration jdbcOperations has to be defined!
 * see @EnableJdbcRepositories::jdbcOperationsRef()
 */
@Configuration
@EnableTransactionManagement
@EnableJdbcRepositories(transactionManagerRef = "clickhouseTransactionManager")
//@EnableJdbcRepositories(basePackages = "com.example.sblqbsclickhouse.clickhouse.db.repo",
//        transactionManagerRef = "clickhouseTransactionManager")
public class ClickhouseDataSourceConfiguration {

    @Bean(name = "clickhouseDataSourceProperties")
    @ConfigurationProperties("spring.datasource.clickhouse")
    public DataSourceProperties clickHouseDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "clickhouseDataSource")
    public DataSource clickhouseDataSource(@Qualifier("clickhouseDataSourceProperties")
                                           DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "clickhouseTransactionManager")
    public PlatformTransactionManager clickhouseTransactionManager(
            @Qualifier("clickhouseDataSource") DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }

    @Bean
    @Primary
    NamedParameterJdbcOperations jdbcOperations(
            @Qualifier("clickhouseDataSource") DataSource sqlServerDs) {
        return new NamedParameterJdbcTemplate(sqlServerDs);
    }

//    @Bean(name = "clickhouseJdbcTemplate")
//    public JdbcTemplate clickhouseJdbcTemplate(
//            @Qualifier("clickhouseDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource, true);
//    }
}
