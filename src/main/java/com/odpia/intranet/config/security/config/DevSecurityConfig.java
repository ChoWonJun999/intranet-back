package com.odpia.intranet.config.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("!dev")
public class DevSecurityConfig {

	@Bean
	public SecurityFilterChain devFilterChain(HttpSecurity http) throws Exception {
		http.cors(Customizer.withDefaults())
		        .csrf(csrf -> csrf.disable())
		        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

		return http.build();
	}
}
