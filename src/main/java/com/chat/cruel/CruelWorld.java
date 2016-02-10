package com.chat.cruel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@EnableAutoConfiguration
@Configuration
@ComponentScan
public class CruelWorld {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CruelWorld.class, args);
    }
}
