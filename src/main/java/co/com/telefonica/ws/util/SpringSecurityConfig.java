package co.com.telefonica.ws.util;

import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * NO BORRAR...
 * 
 * Clase de Configuración de Seguridad del microservicio.
 * 
 * @autor: COE-Arquitectura-Telefonica
 * @date: 19-10-2020
 * @version 1.0.0
 */
@Configuration
public class SpringSecurityConfig {

	@Value("${controller.properties.base-path}")
	private String uriBasePattern;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@SneakyThrows
	public SecurityFilterChain filterChain(HttpSecurity http) {
		String uriPattern = "/" + this.uriBasePattern + "/**";

		http.csrf()
				.and()
				.authorizeRequests()
				.antMatchers(uriPattern)
				.authenticated()
				.and()
				.headers()
				.xssProtection()
				.and()
				.frameOptions()
				.sameOrigin()
				.xssProtection()
				.and()
				.contentSecurityPolicy("script-src 'self'");

		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers(
			"/" + uriBasePattern + "/**", 
			"/v2/api-docs/**", 
			"/swagger-ui/**", 
			"/h2-console/**", 
			"/actuator/**"
		);
	}

}