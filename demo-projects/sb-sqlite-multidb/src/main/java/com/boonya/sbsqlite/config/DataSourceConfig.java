package com.boonya.sbsqlite.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    private static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean(name = "SQLiteDataSource")
    @Qualifier("SQLiteDataSource")
    @ConfigurationProperties(prefix="spring.datasource.sqlite")
    public DataSource SQLiteDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "MySQLDataSource")
    @Qualifier("MySQLDataSource")
    @ConfigurationProperties(prefix="spring.datasource.mysql")
    @Primary
    public DataSource MySQLDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqliteJdbcTemplate")
    public JdbcTemplate sqliteJdbcTemplate(@Qualifier("SQLiteDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "mysqlJdbcTemplate")
    public JdbcTemplate mysqlJdbcTemplate(@Qualifier("MySQLDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
