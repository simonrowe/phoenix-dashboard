package io.pivotal.pa.phoenix.collector.capi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CloudControllerClient {

    @Autowired
    @Qualifier("oauth2RestTemplate")
    private RestTemplate restTemplate;

    public <T> T process(String uri, Class<T> clazz) {
        return restTemplate.getForObject(uri, clazz);
    }
}
