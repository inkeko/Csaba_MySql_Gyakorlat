package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class AppConfig {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in); // Standard inputra épülő Scanner bean
    }
}

