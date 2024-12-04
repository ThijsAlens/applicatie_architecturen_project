package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;


@Configuration
public class SecurityConfig {
	
	@Autowired
	private DataSource dataSource;
	
	
	@Bean
	public SecurityFilterChain beveilig(HttpSecurity http) throws Exception {
		http	.authorizeHttpRequests(authorize -> authorize
					.requestMatchers("/secured/overview").hasRole("admin")
					.requestMatchers("/secured/rental").permitAll()
					.anyRequest().permitAll()
				)
				.formLogin(form -> form
					.loginPage("/login").permitAll()
				);
		return http.build();
	}
	
	@Autowired
	public void dbauth(AuthenticationManagerBuilder auth) throws Exception {
	auth
	.jdbcAuthentication()
	.dataSource(dataSource)
	.authoritiesByUsernameQuery("select EMAIL, AUTHORITY from AA_AUTHORITIES where EMAIL = ?")
	.usersByUsernameQuery("select EMAIL,PASSWD, ENABLED from AA_CUSTOMERS where EMAIL = ?");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
