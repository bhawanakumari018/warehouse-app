package com.assignment.warehouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails user = User
                .withUsername("admin")
                .password("{noop}admin123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    	@Bean
    	public SecurityFilterChain securityFilterChain(
    	        HttpSecurity http) throws Exception {

    	    http
    	        .csrf(csrf -> csrf.disable())
    	        .headers(headers ->
    	                headers.frameOptions(
    	                        frame -> frame.disable()))
    	        .authorizeHttpRequests(auth -> auth
    	                .requestMatchers("/h2-console/**").permitAll()
    	                .anyRequest().authenticated())
    	        .httpBasic(Customizer.withDefaults());

    	    return http.build();
    	}
    
}