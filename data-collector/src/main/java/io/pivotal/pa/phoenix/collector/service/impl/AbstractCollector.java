package io.pivotal.pa.phoenix.collector.service.impl;

import io.pivotal.pa.phoenix.collector.capi.model.AbstractResponse;
import io.pivotal.pa.phoenix.collector.capi.model.Process;
import io.pivotal.pa.phoenix.collector.capi.model.ProcessResponse;
import io.pivotal.pa.phoenix.collector.capi.service.CloudControllerClient;
import io.pivotal.pa.phoenix.collector.service.AggregationChannel;
import io.pivotal.pa.phoenix.collector.service.Collector;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractCollector<T extends AbstractResponse, E> implements Collector {

    @Autowired
    private CloudControllerClient<ProcessResponse> cloudControllerClient;

    @Autowired
    private AggregationChannel<Process> channel;

    @Override
    public void collectAndSend(String cloudControllerUri) {
        AbstractResponse response = cloudControllerClient.process(cloudControllerUri);
        channel.send(response.getResources());
        if (response.hasNextPage()) {
            collectAndSend(response.getNextPageUri());
        }
    }
}
