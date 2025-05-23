package dev.scastillo.franchise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FranchiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FranchiseApplication.class, args);
	}

}
