package com.hq.multi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HqMultiProviderApplication {

    public static void main(String[] args) {

        SpringApplication.run(HqMultiProviderApplication.class, args);
    }

}
