package com.project.creation.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.project.creation.Filters.JwtFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/Authentication/signup","/Authentication/login").permitAll()
            .requestMatchers("/mainController/getUserName").hasAuthority("ROLE_CUSTOMER")
            .requestMatchers("/AdminAccess-CustomerToAgent/{CustomerId}","/AdminAccess-AgentToAdmin/{AgentId}").hasAuthority("ROLE_ADMIN")
            .anyRequest().authenticated()
        )
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // <--- disable sessions
        .formLogin(form -> form.disable()) // <--- disable form login
        .httpBasic(basic -> basic.disable()) // <--- disable basic auth too
        .addFilterBefore(new JwtFilter(), org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);
    return http.build();
}

@Bean
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}
}
