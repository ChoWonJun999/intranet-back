package com.odpia.intranet.config.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.odpia.intranet.config.security.jwt.JwtAuthFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class ProdSecurityConfig {

	private final JwtAuthFilter jwtAuthFilter;

	private static final String[] WHITE_LIST = { "/", "/error", "/favicon.ico", "/api/auth/**", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**" };

	@Bean
	public SecurityFilterChain prodFilterChain(HttpSecurity http) throws Exception {
		http.cors(Customizer.withDefaults())
		        .csrf(csrf -> csrf.disable())
		        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		        .authorizeHttpRequests(auth -> auth.requestMatchers(WHITE_LIST).permitAll().anyRequest().authenticated())
		        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
