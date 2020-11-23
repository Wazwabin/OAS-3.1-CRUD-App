package ru.vallione.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().
                withUser("user").password("{noop}1").roles("USER")
                .and()
                .withUser("admin").password("{noop}2").roles("ADMIN");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/client/**")// /client/**
                .authorizeRequests()
                .anyRequest().hasRole("USER")
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}