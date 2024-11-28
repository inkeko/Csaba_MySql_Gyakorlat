package org.example;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.example.services.ApplicationService;

@SpringBootApplication(scanBasePackages = "org.example")
public class Main {

    public static void main(String[] args) {
        // Spring alkalmazás indítása és az ApplicationContext megszerzése
        ApplicationContext context = SpringApplication.run(Main.class, args);

        // Az ApplicationService példányának lekérése a Spring kontextusból
        ApplicationService appService = context.getBean(ApplicationService.class);

        // Alkalmazás indítása
        appService.start();
    }
}
