package com.jayus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BootNocascompatibilityApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootNocascompatibilityApplication.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread( () ->{
            System.out.println("jvm 关闭钩子执行");
        }));
    }

}
