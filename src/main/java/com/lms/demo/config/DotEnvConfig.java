package com.lms.demo.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class DotEnvConfig {

    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        Dotenv dotenv = Dotenv.load();

        PropertySourcesPlaceholderConfigurer configurer =
                new PropertySourcesPlaceholderConfigurer();

        dotenv.entries().forEach(e ->
                System.setProperty(e.getKey(), e.getValue())
        );

        return configurer;
    }
}
