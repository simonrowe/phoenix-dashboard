package io.pivotal.pa.phoenix.datacollector.service.impl;

import io.pivotal.pa.phoenix.datacollector.service.AggregationChannel;
import io.pivotal.pa.phoenix.datacollector.uaa.model.Process;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ProcessAggregationChannel implements AggregationChannel<Process> {


    @Value("${foundation.id}")
    private String foundationId;

    @Override
    public void send(List<Process> objects) {
        log.info("Sending the folllowing objects to the aggregator:" + foundationId);
        log.info(objects.toString());
    }
}
