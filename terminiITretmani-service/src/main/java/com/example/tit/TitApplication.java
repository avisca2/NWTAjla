package com.example.tit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.netflix.eureka.*;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class TitApplication {

	public static void main(String[] args) {
		SpringApplication.run(TitApplication.class, args);
	}

}
