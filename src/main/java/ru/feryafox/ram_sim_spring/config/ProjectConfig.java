package ru.feryafox.ram_sim_spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "ru.feryafox.ram_sim_spring.RamSimManager")
@ComponentScan(basePackages = "ru.feryafox.ram_sim_spring.controllers")
public class ProjectConfig {

}
