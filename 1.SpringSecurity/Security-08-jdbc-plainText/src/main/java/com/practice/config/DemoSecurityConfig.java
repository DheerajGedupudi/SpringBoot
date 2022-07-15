package com.practice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter
{

	@Autowired
	private DataSource securityDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// add our users for in memory authentication
		
//		UserBuilder users = User.withDefaultPasswordEncoder();
//
//		auth.inMemoryAuthentication()
//			.withUser(users.username("Suresh").password("abcd").roles("EMPLOYEE"))
//			.withUser(users.username("Ramesh").password("abcd").roles("EMPLOYEE", "MANAGER"))
//			.withUser(users.username("Mahesh").password("abcd").roles("EMPLOYEE","MANAGER","ADMIN"));

		//use jdbc now

		auth.jdbcAuthentication().dataSource(securityDataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/").hasRole("EMPLOYEE")
				.antMatchers("/leaders/**").hasRole("MANAGER")
				.antMatchers("/systems/**").hasRole("ADMIN")
				.and()
				.formLogin()
					.loginPage("/showLoginPage")
					.loginProcessingUrl("/authenticateTheUser")
					.permitAll()
				.and()
				.logout()
				.permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/access-denied");
		
	}
		
}






