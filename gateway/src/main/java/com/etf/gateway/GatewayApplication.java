package com.etf.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

//	public RouteLocator test(RouteLocatorBuilder builder) {
//		return builder.routes()
//		  .route("r1", p -> p
//		  .path("/doktori/**")
//		  .uri("lb://doktori"))
//		  .build();
//	}

}
