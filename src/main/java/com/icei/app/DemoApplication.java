package com.icei.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "<html style='background-color: #1e1e1e; color: #00ff00; font-family: monospace; text-align: center; padding: 50px;'>" +
               "<h1>☕ ¡Backend Java Spring Boot!</h1>" +
               "<p>Compilado con Maven, empaquetado en Docker y desplegado automáticamente por Jenkins.</p>" +
               "</html>";
    }
}