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

    @Value("${ingestor.uri}")
    private String ingestorUri;

    @Value("${ingestor.ai.path}")
    private String ingestorPath;


    @Autowired
    @Qualifier("ingestorRestTemplate")
    private RestTemplate restTemplate;


    @Override
    public void send(List objects) {
        log.info("Sending the folllowing objects to the aggregator:" + foundationId);
        restTemplate.postForEntity(ingestorUri + ingestorPath,
                objects.stream().map(obj -> {
                    Process p = (Process)obj;
                    return new ApplicationInstance(null, p.getGuid(), p.getInstances(), foundationId, null);
                }).collect(toList()), String.class);

    }
}
