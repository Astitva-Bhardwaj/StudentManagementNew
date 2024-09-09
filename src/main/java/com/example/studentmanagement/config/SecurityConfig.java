package com.example.studentmanagement.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/login", "/swagger-ui/**", "/v3/api-docs/**").permitAll() // Allow access to Swagger
                        .requestMatchers("/students/**").hasRole("USER") // Only users with role USER can access students data
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/students", true) // Redirect to /students after successful login
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll); // Allow logout for all users

        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        // In-memory users with roles USER and ADMIN
        var user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        var admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}

