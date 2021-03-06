package io.pivotal.pa.phoenix.collector;


import io.pivotal.pa.phoenix.collector.capi.service.impl.CapiUriBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
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

    @Bean("oauth2RestTemplate")
    public OAuth2RestTemplate oauth2RestTemplate(@Qualifier("uaaClientCredentials") ClientCredentialsResourceDetails oAuth2ProtectedResourceDetails) {
        return new OAuth2RestTemplate(oAuth2ProtectedResourceDetails);
    }

    @Bean("ingestorRestTemplate")
    public RestTemplate restTemplate(RestTemplateBuilder builder, @Value("${ingestor.security.username}") String user, @Value("${ingestor.security.password}") String pass) {
        return builder.basicAuthentication(user, pass).build();
    }

    @Bean("processUriBuilder")
    public CapiUriBuilder processUriBuilder(@Value("${capi.processes.path}") String capiProcessPath) {
        return new CapiUriBuilder(capiProcessPath);
    }

}
