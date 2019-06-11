package br.com.crcarvalho.incidentes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
			.antMatchers("/webjars/bootstrap/4.3.1/css/bootstrap.min.css").permitAll()
			.anyRequest().authenticated()
			.and().formLogin().permitAll();
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
			.inMemoryAuthentication()
			.withUser("admin").password("abc123").roles("ADMIN")
			.and()
			.withUser("user").password("123456").roles("USER");
		
	}
	
	
	
	
	
}
