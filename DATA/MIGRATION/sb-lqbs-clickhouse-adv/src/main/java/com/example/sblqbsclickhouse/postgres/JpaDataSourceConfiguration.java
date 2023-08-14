package com.example.sblqbsclickhouse.postgres;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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


@Configuration
//@EnableTransactionManagement
//@EnableJdbcRepositories(basePackages = "com.example.sblqbsclickhouse.postgres.repo")
//@EnableJpaRepositories(basePackages = "com.example.sblqbsclickhouse.postgres.repo",
//        entityManagerFactoryRef = "primaryEntityManagerFactory",
//        transactionManagerRef = "primaryTransactionManager")

//@ComponentScan(basePackages = "com.example.sblqbsclickhouse.postgres")
//@EntityScan(basePackages = "com.example.sblqbsclickhouse.postgres.entities")
public class JpaDataSourceConfiguration {

    /**
     * Returns data source properties
     *
     * @return {@link DataSourceProperties}
     */

    @Bean(name = "postgresDataSourceProperties")
    @ConfigurationProperties("spring.datasource.postgres")
    public DataSourceProperties postgresDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * Return data source
     *
     * @param dataSourceProperties {@link DataSourceProperties}
     * @return {@link DataSource}
     */

    @Bean(name = "postgresDataSource")
    public DataSource postgresDataSource(@Qualifier("postgresDataSourceProperties")
                                        DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }



    /**
     * Returns jdbc template
     *
     * @param dataSource {@link DataSource}
     */
    @Bean(name = "pgJdbcTemplate")
    public JdbcTemplate pgJdbcTemplate(
            @Qualifier("postgresDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource, true);
    }

    /**
     * Return named parameter jdbc operations
     *
     * @param dataSource {@link DataSource}
     */
    @Bean(name = "pgJdbcOperations")
    public NamedParameterJdbcOperations namedParameterJdbcOperations(
            @Qualifier("postgresDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

//    @Bean(name = "postgresTransactionManager")
//    public PlatformTransactionManager postgresTransactionManager(
//            @Qualifier("postgresDataSource") DataSource dataSource) {
//        return new JdbcTransactionManager(dataSource);
//    }

}
