package io.pivotal.pa.phoenix.collector.capi.service.impl;

import io.pivotal.pa.phoenix.collector.capi.service.ProcessClient;
import io.pivotal.pa.phoenix.collector.capi.model.ProcessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProcessClientImpl implements ProcessClient {

    @Autowired
    @Qualifier("oauth2RestTemplate")
    private RestTemplate restTemplate;

    @Override
    public ProcessResponse process(String uri) {
        return restTemplate.getForObject(uri, ProcessResponse.class);
    }
}
