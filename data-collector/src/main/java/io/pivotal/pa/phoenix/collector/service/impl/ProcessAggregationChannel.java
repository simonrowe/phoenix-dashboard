package io.pivotal.pa.phoenix.collector.service.impl;

import io.pivotal.pa.phoenix.collector.service.AggregationChannel;
import io.pivotal.pa.phoenix.collector.capi.model.Process;
import io.pivotal.pa.phoenix.model.ApplicationInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@Slf4j
public class ProcessAggregationChannel implements AggregationChannel<Process> {


    @Value("${foundation.id}")
    private String foundationId;

    @Value("${injestion.uri}")
    private String injestionUri;

    @Value("${injestion.ai.path}")
    private String injestionPath;


    @Autowired
    @Qualifier("injestorRestTemplate")
    private RestTemplate restTemplate;


    @Override
    public void send(List<Process> objects) {
        log.info("Sending the folllowing objects to the aggregator:" + foundationId);
        restTemplate.postForEntity(injestionUri + injestionPath,
                objects.stream().map(p -> new ApplicationInstance(null, p.getGuid(), p.getInstances(), foundationId, null))
                        .collect(toList()), String.class);

    }
}
