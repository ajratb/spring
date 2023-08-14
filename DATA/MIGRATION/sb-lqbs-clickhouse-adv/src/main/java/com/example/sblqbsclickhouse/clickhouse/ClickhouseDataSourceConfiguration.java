package com.example.sblqbsclickhouse.clickhouse;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


/**
 * Represents configuration for clickhouse data source
 */
@Configuration
@EnableTransactionManagement
@EnableJdbcRepositories(basePackages = "com.example.sblqbsclickhouse.clickhouse.db.repo",
        transactionManagerRef = "clickhouseTransactionManager")
@ComponentScan(basePackages = "com.example.sblqbsclickhouse.clickhouse.db")
@EntityScan(basePackages = "com.example.sblqbsclickhouse.clickhouse.db.entities")
public class ClickhouseDataSourceConfiguration {

    @Bean(name = "clickhouseDataSourceProperties")
    @ConfigurationProperties("spring.datasource.clickhouse")
    public DataSourceProperties clickHouseDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "clickhouseDataSource")
    public DataSource clickhouseDataSource(@Qualifier("clickhouseDataSourceProperties")
                                           DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "clickhouseJdbcTemplate")
    public JdbcTemplate clickhouseJdbcTemplate(
            @Qualifier("clickhouseDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource, true);
    }

    @Bean(name = "clickhouseTransactionManager")
    public PlatformTransactionManager clickhouseTransactionManager(
            @Qualifier("clickhouseDataSource") DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }

}
