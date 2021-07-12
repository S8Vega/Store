package com.pragma.serviciocliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@RibbonClient(name = "servicio-foto")
@EnableFeignClients
@SpringBootApplication
public class PracticaBackendJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticaBackendJavaApplication.class, args);
    }

}
