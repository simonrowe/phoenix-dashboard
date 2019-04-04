package io.pivotal.pa.phoenix.ingestor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "io.pivotal.pa.phoenix")
public class IngestorApplication {

	public static void main(String[] args) {
		SpringApplication.run(IngestorApplication.class, args);
	}

}
