//package com.to_do_app;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


//// This links the spring backend to the react frontend.
//// localhost:3000/ is react's port
//// google 'spring boot cors' to get this code

//@Configuration
//public class MyConfiguration {
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new (WebMvcConfigurerAdapter) {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping( pathPattern: "localhost:3000/").allowedMethods("GET", "POST", "PUT", "DELETE");
//            }
//        };
//    }
//}
