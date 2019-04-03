package io.pivotal.pa.phoenix.injestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "io.pivotal.pa.phoenix")
public class InjestionApplication {

	public static void main(String[] args) {
		SpringApplication.run(InjestionApplication.class, args);
	}

}
