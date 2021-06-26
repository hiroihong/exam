package com.example.demo.datasource;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// mybatis-jdbc가 알아서 해줌
// mybatis-jdbc는 spring boot에 들어있음
/*
 * @Configuration public class DataSourceConfig {
 * 
 * @Bean
 * 
 * @ConfigurationProperties(prefix = "spring.datasource") public DataSource
 * MariaData() { return DataSourceBuilder.create().build(); } }
 */