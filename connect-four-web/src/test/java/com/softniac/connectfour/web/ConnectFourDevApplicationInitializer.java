package com.softniac.connectfour.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.softniac.connectfour.web.config.ConnectFourWebConfig;

@Configuration
@Import({ ConnectFourWebConfig.class })
@PropertySource({ "classpath:connect-four.properties" })
public class ConnectFourDevApplicationInitializer {

    public static void main(String... args) {
	SpringApplication.run(ConnectFourDevApplicationInitializer.class, args);
    }
}