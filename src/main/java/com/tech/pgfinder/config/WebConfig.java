package com.tech.pgfinder.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Add your frontend origin here
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Add your HTTP methods here
                .allowedHeaders("*"); // Add your request headers here
    }
}
