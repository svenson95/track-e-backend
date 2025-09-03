package com.svenson95.track_e_backend.config;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.PostConstruct;

@Configuration
public class CorsConfig {
    static String DEV_ENV = "http://localhost:8100";
    static String PROD_ENV = System.getenv("PROD_ENV");

    String[] origins = Arrays.stream(new String[]{DEV_ENV, PROD_ENV})
                    .filter(Objects::nonNull)
                    .toArray(String[]::new);

    @PostConstruct
    public void logEnv() {
        System.out.println(">>> PROD_ENV = " + PROD_ENV);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins(origins)
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*");
            }
        };
    }
}
