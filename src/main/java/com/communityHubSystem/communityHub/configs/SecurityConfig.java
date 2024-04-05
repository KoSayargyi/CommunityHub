package com.communityHubSystem.communityHub.configs;

import com.communityHubSystem.communityHub.models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> login
                .loginPage("/signIn")
                .usernameParameter("staff_id")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/"));
        http.csrf(AbstractHttpConfigurer::disable);
        http.logout(log -> log
                .logoutUrl("/logout")
                .logoutSuccessUrl("/"));
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/signIn", "/ws/**", "/assets/**", "/forms/**","/static/**").permitAll()
                .requestMatchers("/css/**","/img/**","/js/**","/scss/**","/vendor/**").permitAll()
                .requestMatchers("/user/**").hasAnyAuthority(User.Role.USER.name(), User.Role.ADMIN.name())
                .requestMatchers("/admin/**").hasAnyAuthority(User.Role.ADMIN.name())
                .anyRequest().permitAll());
        http.exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                httpSecurityExceptionHandlingConfigurer.accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.sendRedirect("/access-denied");
                }));
        return http.build();
    }
}
