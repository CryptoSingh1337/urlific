package com.lunaticdevs.urlific;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.lunaticdevs.urlific.config.credential")
public class UrlificApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlificApplication.class, args);
    }
}
