package ru.feryafox.ram_sim_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class RamSimSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(RamSimSpringApplication.class, args);
    }

}
