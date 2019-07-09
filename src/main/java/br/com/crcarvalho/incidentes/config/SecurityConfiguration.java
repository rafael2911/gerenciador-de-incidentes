package br.com.crcarvalho.incidentes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
			.antMatchers("/webjars/**").permitAll()
			.antMatchers("/h2/**").permitAll()
			.antMatchers("/usuario/**").hasRole("ADMIN")
			.antMatchers("/chamado/todos/**").hasAnyRole("ADMIN", "TECNICO")
			.anyRequest().authenticated()
			.and().formLogin().permitAll()
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
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
