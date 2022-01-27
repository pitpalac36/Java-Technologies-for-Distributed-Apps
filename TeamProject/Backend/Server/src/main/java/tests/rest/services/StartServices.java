package tests.rest.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class StartServices {
    public static void main(String[] args) {
        System.out.println("hey");
        SpringApplication.run(StartServices.class, args);
    }
}