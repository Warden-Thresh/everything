package com.warden.common.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * @author YangJiaYing
 * @date 2019/05/18
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "a")
    public DataSource datasource(Environment env) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(env.getProperty("spring.datasource.a.url"));
        ds.setUsername(env.getProperty("spring.datasource.a.username"));
        ds.setPassword(env.getProperty("spring.datasource.a.password"));
        ds.setDriverClassName(env.getProperty("spring.datasource.a.driver-class-name"));
        return ds;
    }

//    @Bean(name = "b")
//    public DataSource datasourceOther(Environment env) {
//        HikariDataSource ds = new HikariDataSource();
//        ds.setJdbcUrl(env.getProperty("spring.datasource.b.url"));
//        ds.setUsername(env.getProperty("spring.datasource.b.username"));
//        ds.setPassword(env.getProperty("spring.datasource.b.password"));
//        ds.setDriverClassName(env.getProperty("spring.datasource.b.driver-class-name"));
//        return ds;
//    }
}