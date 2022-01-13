package com.systempro.pagament;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class PagamentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagamentApplication.class, args);
	}

}
