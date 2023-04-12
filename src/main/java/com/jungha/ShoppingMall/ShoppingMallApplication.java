package com.jungha.ShoppingMall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableJpaAuditing
@SpringBootApplication
public class ShoppingMallApplication {


	public static void main(String[] args) {
		SpringApplication.run(ShoppingMallApplication.class, args);
	}

}
