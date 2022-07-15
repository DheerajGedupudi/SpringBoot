package com.practice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
//said depreciated before
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        //said depreciated before
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(users.username("Ramesh").password("abcd").roles("EMPLOYEE"))
                .withUser(users.username("Suresh").password("abcd").roles("MANAGER"))
                .withUser(users.username("Mahesh").password("abcd").roles("ADMIN"));
    }
}
