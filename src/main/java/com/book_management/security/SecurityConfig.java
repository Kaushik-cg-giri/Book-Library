package com.book_management.security;



import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import com.book_management.service.customUserDetailService;

@Configuration
public class SecurityConfig {

	   private final customUserDetailService userDetailsService;
	   

	    public SecurityConfig(customUserDetailService userDetailsService) {
	        this.userDetailsService = userDetailsService;	        
	    }
	    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    

 /*   @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }*/
/*    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .httpBasic(); // Use Basic Authentication

        return http.build();
    }
    

}

/*
  [User Request] 
 
        |
        v
[Spring Security Filter Chain]
        |
        v
[UsernamePasswordAuthenticationFilter]
        |
        v
[AuthenticationManager (default ProviderManager)]
        |
        v
[AuthenticationProvider (default DaoAuthenticationProvider)]
        |
        v
[UserDetailsService -> loadUserByUsername]
        |
        v
[Password Match Check]
        |
 +------|------+
 |             |
Success       Failure
 |             |
 v             v
[SecurityContext]  [401 Unauthorized]

*/