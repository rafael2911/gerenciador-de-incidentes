package br.com.crcarvalho.incidentes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@SpringBootApplication
public class GerenciadorDeIncidentesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorDeIncidentesApplication.class, args);
	}
	
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

}
