package com.to_do_app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


// From Velican's project, but I dont know when/how to implement
// its dependencies. Also, WebMvcConfigurerAdapter is deprecated.

//@Configuration
//public class MyConfiguration {
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new (WebMvcConfigurerAdapter) {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping( pathPattern: "/**").allowedMethods("GET", "POST", "PUT", "DELETE");
//            }
//        };
//    }
//}
