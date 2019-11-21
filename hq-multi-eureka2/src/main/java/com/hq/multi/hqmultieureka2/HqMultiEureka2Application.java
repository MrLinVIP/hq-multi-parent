package com.hq.multi.hqmultieureka2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class HqMultiEureka2Application {

	public static void main(String[] args) {
		SpringApplication.run(HqMultiEureka2Application.class, args);
	}

}
