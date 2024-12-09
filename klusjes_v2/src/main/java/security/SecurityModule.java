package security;
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
	@Bean
	public PasswordEncoder passwordEncoder() {
	return NoOpPasswordEncoder.getInstance();
	}
	
	
	@Bean
	public SecurityFilterChain beveilig(HttpSecurity http) throws Exception {
	http
	.authorizeHttpRequests(authorize -> authorize
			.requestMatchers("/*").permitAll()
	)
	.formLogin( form -> form 
			.loginPage("/login").permitAll())
	.logout(logout -> logout
			.logoutSuccessUrl("/index"))
			;
	return http.build();
	}
	

}
