package com.hq.eureka.hqmultieureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class HqMultiEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HqMultiEurekaApplication.class, args);
	}

}
