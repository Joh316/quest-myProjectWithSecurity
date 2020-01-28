
package com.wildcodeschool.myProjectWithSecurity.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests()
	          .antMatchers("/admin/**").hasRole("ADMIN")
	          .antMatchers("/avengers/assemble/**").hasRole("AVENGER")
	          .antMatchers("/secret-bases/**").hasRole("DIRECTOR")
	          //.anyRequest().authenticated()
	          .and()
	        .formLogin()
	            .and()
	        .httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		// Create names, passwords, and roles for:  
	    // Steve Guy, 
	    // Tony Stark, 
	    // Nick Fury Director of S.H.I.E.L.D..		
	    
		auth.inMemoryAuthentication()
	    .withUser("Steve Guy")
	        .password(encoder.encode("sg"))
	        .roles("")
	        .and()
	    .withUser("Tony Stark")
	        .password(encoder.encode("ts"))
	        .roles("AVENGER")
	        .and()
		.withUser("Nick Fury")
        	.password(encoder.encode("nf"))
        	.roles("DIRECTOR");
	}
	
}
