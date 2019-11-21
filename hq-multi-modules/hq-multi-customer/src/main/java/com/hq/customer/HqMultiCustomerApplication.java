package com.hq.customer;

import com.hq.customer.fegin.ProviderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
public class HqMultiCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HqMultiCustomerApplication.class, args);
	}

}
