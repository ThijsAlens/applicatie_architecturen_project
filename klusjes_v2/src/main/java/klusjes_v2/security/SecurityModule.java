package klusjes_v2.security;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityModule {

	@Autowired
	DataSource datasource;
	
	
	@Bean
	public SecurityFilterChain beveilig(HttpSecurity http) throws Exception {
	http
	.authorizeHttpRequests(authorize -> authorize
			.requestMatchers("/**").permitAll()
	)
	.formLogin(form -> form 
			.loginPage("/login_").permitAll()
			)
	.logout(logout -> logout
			.logoutSuccessUrl("/").permitAll()
			)
	.csrf().disable();
	return http.build();
	}
	
    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .jdbcAuthentication()
            .dataSource(datasource)
            .usersByUsernameQuery("SELECT username, password FROM people WHERE username = ?")
            .passwordEncoder(NoOpPasswordEncoder.getInstance());  // No password encoder for plain text passwords
    }
	

}
