package com.dioneybecker.msscbeerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(exclude = ContextRegionProviderAutoConfiguration.class)
public class MsscBeerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsscBeerServiceApplication.class, args);
	}

	
}
