package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@Configuration
public class AppConfig {

    /*@Bean
    public UserService getUserService() {
        System.out.println("In method getUserService");
        return new UserService();
    }*/
}
