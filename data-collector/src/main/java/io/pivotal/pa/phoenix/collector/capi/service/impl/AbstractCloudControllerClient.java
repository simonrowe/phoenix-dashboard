package io.pivotal.pa.phoenix.collector.capi.service.impl;

import io.pivotal.pa.phoenix.collector.capi.model.AbstractResponse;
import io.pivotal.pa.phoenix.collector.capi.service.CloudControllerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;

public class AbstractCloudControllerClient<T extends AbstractResponse> implements CloudControllerClient {

    private Class<T> clazz;

    public AbstractCloudControllerClient() {
        this.clazz = (Class<T>)
                ((ParameterizedType)getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }

    @Autowired
    @Qualifier("oauth2RestTemplate")
    private RestTemplate restTemplate;

    @Override
    public T process(String uri) {
        return restTemplate.getForObject(uri, clazz);
    }
}
