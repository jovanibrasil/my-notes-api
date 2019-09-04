package com.notes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

//@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
//	@Autowired
//	private ExceptionHandlerFilter exceptionHandlerFilter;
//	
//	Autowired
//	private CustomAccessDeniedHandler accessDeniedHandler;
	
	/*
	 * Filter used when the application intercepts a requests.
	 */
	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		return new JwtAuthenticationTokenFilter();
	}
	
	@Bean
	public ExceptionHandlerFilter exceptionHandlerFilterBean() throws Exception {
		return new ExceptionHandlerFilter();
	}
	
	/**
	 * Configure request authorization
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
				
		http
			.csrf().disable() // disable csrf (cross-site request forgery) 
			.cors()
			.and()
			.addFilterBefore(authenticationTokenFilterBean(),  BasicAuthenticationFilter.class)
			.addFilterBefore(exceptionHandlerFilterBean(), JwtAuthenticationTokenFilter.class)
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler) // set authentication error
		//	.accessDeniedHandler(accessDeniedHandler)
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // set session police stateless
			.and()
			.authorizeRequests()
			.antMatchers("/notebooks", "/notebooks/**", "/notes", "/notes/**").hasAnyRole("ADMIN", "USER")
			.antMatchers("/users").hasRole("SERVICE");
			//http.addFilterBefore(new LoginFilter("/users/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class);
			http.headers().cacheControl();
		
	}

}
