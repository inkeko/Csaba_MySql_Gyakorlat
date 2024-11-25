package org.example;

;
import org.example.services.ApplicationService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationService appService = new ApplicationService();
        appService.start();
    }
}