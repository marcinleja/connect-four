package com.softniac.connectfour.core;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ConnectFourCoreConfig {

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.username}")
    private String dbUsername;

    @Value("${db.password}")
    private String dbPassword;

    @Value("${db.driverClassName}")
    private String dbDriverClassName;

    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
	return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() {
	HikariConfig hikariConfig = new HikariConfig();
	hikariConfig.setDriverClassName(dbDriverClassName);
	hikariConfig.setJdbcUrl(dbUrl);
	hikariConfig.setUsername(dbUsername);
	hikariConfig.setPassword(dbPassword);
	hikariConfig.setConnectionTestQuery("SELECT 1");
	hikariConfig.setMaximumPoolSize(20);
	hikariConfig.setPoolName("springHikariCP");

	hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
	hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
	hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");

	HikariDataSource dataSource = new HikariDataSource(hikariConfig);

	return dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }

}
