package sb.data.multipledatasources.postgres;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = "com.example.sblqbsclickhouse.postgres.repo",
@EnableJpaRepositories (
        entityManagerFactoryRef = "pgEntityManagerFactory",
        transactionManagerRef = "pgTransactionManager")

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
    @Primary
    @Bean(name = "pgDataSource")
    public DataSource pgDataSource(@Qualifier("postgresDataSourceProperties")
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
            @Qualifier("pgDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource, true);
    }

    /**
     * Return named parameter jdbc operations
     *
     * @param dataSource {@link DataSource}
     */
    @Bean(name = "pgJdbcOperations")
    public NamedParameterJdbcOperations namedParameterJdbcOperations(
            @Qualifier("pgDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

//    @Bean(name = "pgTransactionManager")
//    public PlatformTransactionManager pgTransactionManager(
//            @Qualifier("pgDataSource") DataSource dataSource) {
//        return new JpaTransactionManager();
//    }

    @Bean(name = "pgEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean todosEntityManagerFactory(
            @Qualifier("pgDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("sb.data.multipledatasources.postgres")
                .persistenceUnit("testdb")
//                .packages(Genre.class)
                .build();
    }

    @Bean(name = "pgTransactionManager")
    public PlatformTransactionManager todosTransactionManager(
            @Qualifier("pgEntityManagerFactory") LocalContainerEntityManagerFactoryBean todosEntityManagerFactory) {

        return new JpaTransactionManager(Objects.requireNonNull(todosEntityManagerFactory.getObject()));
    }

}

