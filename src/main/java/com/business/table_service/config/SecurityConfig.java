package com.business.table_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/tables/prices/add").permitAll()
                        .requestMatchers("/api/tables/prices/all").permitAll()
                        .requestMatchers("/api/tables/prices/update/{id}").permitAll()
                        .requestMatchers("/api/tables/prices/delete/{id}").permitAll()
                        .requestMatchers("/api/tables/types/add").permitAll()
                        .requestMatchers("/api/tables/types/all").permitAll()
                        .requestMatchers("/api/tables/types/update/{id}").permitAll()
                        .requestMatchers("/api/tables/types/delete/{id}").permitAll()
//                                .requestMatchers("/api/tables/types/{typeId}").permitAll()

                        .requestMatchers("/api/tables/add").permitAll()
                        .requestMatchers("/api/tables/all").permitAll()
//                        .requestMatchers("/api/tables/{id}").permitAll()
                        .requestMatchers("/api/tables/update/{id}").permitAll()
                        .requestMatchers("/api/tables/with-prices").permitAll()
                                .requestMatchers("/api/tables/with-type-price/{id}").permitAll()
                                .requestMatchers("/api/tables/update/{id}/status").permitAll()


                        .anyRequest().authenticated()

                );





        return http.build();
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
