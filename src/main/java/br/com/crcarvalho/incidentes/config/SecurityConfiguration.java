package br.com.crcarvalho.incidentes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.crcarvalho.incidentes.service.UsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
			.antMatchers("/webjars/bootstrap/4.3.1/css/bootstrap.min.css").permitAll()
			.antMatchers("/h2/**").permitAll()
			.anyRequest().authenticated()
			.and().formLogin().permitAll();
		
		http.csrf().disable();
        http.headers().frameOptions().disable();
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
			.userDetailsService(usuarioService)
			.passwordEncoder(new BCryptPasswordEncoder());
		
	}
	
	
	
	
	
}
