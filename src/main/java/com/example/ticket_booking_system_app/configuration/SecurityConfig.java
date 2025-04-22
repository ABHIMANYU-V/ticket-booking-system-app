package com.example.ticket_booking_system_app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(authz -> authz
					.requestMatchers("/events").authenticated()
					.requestMatchers("/bookings").authenticated()
					.anyRequest().authenticated()
			)
			.formLogin()
			.and()
			.httpBasic();
			
		return http.build();
		
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withUsername("user")
							.password(passwordEncoder().encode("password"))
							.roles("USER")
							.build();
		
	    UserDetails admin = User.withUsername("admin")
	    						.password(passwordEncoder().encode("admin123"))
	    						.roles("ADMIN")
	    						.build();
	    
	    return new InMemoryUserDetailsManager(user,admin);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
	}

}
