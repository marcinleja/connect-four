package com.softniac.connectfour.web.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.softniac.connectfour.core.ConnectFourCoreConfig;

@Configuration
@EnableWebMvc
@Import({ WebSecurityConfig.class, ConnectFourCoreConfig.class })
@EnableAutoConfiguration
@ComponentScan(basePackages = {
	"com.softniac.connectfour.web" }, includeFilters = @Filter(Controller.class), excludeFilters = @Filter(Configuration.class))
public class ConnectFourWebConfig extends WebMvcAutoConfiguration {
}
