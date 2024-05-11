package com.paf.paperrex.TT.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
    
@Configuration
// @EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    //     return http
    //     .csrf(csrf->csrf.disable())
    //     .authorizeHttpRequests(auth->
    //     {
    //         auth.requestMatchers("/").permitAll();
    //         auth.requestMatchers("/api/v1").permitAll();
    //         auth.anyRequest().authenticated();
    //     })
    //     .oauth2Login(Customizer.withDefaults())
    //     .cors(cors -> cors.disable())
    //     .build();
    // }

    
}
