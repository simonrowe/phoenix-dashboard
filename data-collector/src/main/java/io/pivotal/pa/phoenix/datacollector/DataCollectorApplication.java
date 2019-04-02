package io.pivotal.pa.phoenix.datacollector;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class DataCollectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataCollectorApplication.class, args);
	}

	@Bean
	@Qualifier("uaaClientCredentials")
	@ConfigurationProperties("security.oauth2.client.uaa")
	public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
		return new ClientCredentialsResourceDetails();
	}

	@Bean
	public OAuth2RestTemplate oauth2RestTemplate(@Qualifier("uaaClientCredentials") ClientCredentialsResourceDetails oAuth2ProtectedResourceDetails) {
		return new OAuth2RestTemplate(oAuth2ProtectedResourceDetails);
	}

}
