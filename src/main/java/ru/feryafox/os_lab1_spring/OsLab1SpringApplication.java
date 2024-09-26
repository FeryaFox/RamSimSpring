package ru.feryafox.os_lab1_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class OsLab1SpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(OsLab1SpringApplication.class, args);
    }

}
