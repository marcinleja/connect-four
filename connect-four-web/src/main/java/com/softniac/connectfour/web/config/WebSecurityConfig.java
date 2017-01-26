package com.softniac.connectfour.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	//@formatter:off
	http.csrf().disable()
	.authorizeRequests()

                .antMatchers("/register")
                    .permitAll()
    	
    		.anyRequest().authenticated()
    		
    		
    		.and()
    			.formLogin()
    			.loginPage("/login.html")
    			.loginProcessingUrl("/login")
    			.failureUrl("/login.html#/?error=incorrectLoginOrPassword")
    			.defaultSuccessUrl("/", true)
    			.usernameParameter("j_username")
    			.passwordParameter("j_password")
    			.permitAll()
    		.and()
    			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    			.logoutSuccessUrl("/")
    			.invalidateHttpSession(true);
	// @formatter:on
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
    
}