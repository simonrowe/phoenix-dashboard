package io.pivotal.pa.phoenix.datacollector.uaa.service.impl;

import io.pivotal.pa.phoenix.datacollector.uaa.service.ProcessClient;
import io.pivotal.pa.phoenix.datacollector.uaa.model.ProcessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProcessClientImpl implements ProcessClient {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ProcessResponse process(String uri) {
        return restTemplate.getForObject(uri, ProcessResponse.class);
    }
}
