# Два и более источников данных в одном проекте

### Reference Documentation
Инструкции:

* [Baeldung: Configure and Use Multiple DataSources in Spring Boot](https://www.baeldung.com/spring-boot-configure-multiple-datasources)
* [Spring Data JDBC with two datasources](https://dba-presents.com/jvm/java/242-spring-data-jdbc-with-two-datasources)

### Проблема работы с репозиториями
Как только подключаем конфигурацию постгрес, репозиторий clickhouse начинает работать через datasource постгреса

Ошибка: не могу найти отношение "simple" (таблица clickhouse)

Проблема в настройке jdbc-репозиториев (@EnableJdbcRepositories). В инструкции baeldung этого нет. Для  jdbc репозиториев нужно указвать jdbcOperations бин (в @EnableJdbcRepositories::jdbcOperationsRef())

Пример в baeldung для jpa репозиториев
@EnableJpaRepositories(
  basePackageClasses = Todo.class,
  entityManagerFactoryRef = "todosEntityManagerFactory",
  transactionManagerRef = "todosTransactionManager"
