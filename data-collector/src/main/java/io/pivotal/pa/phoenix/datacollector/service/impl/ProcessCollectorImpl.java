package io.pivotal.pa.phoenix.datacollector.service.impl;

import io.pivotal.pa.phoenix.datacollector.service.AggregationChannel;
import io.pivotal.pa.phoenix.datacollector.service.ProcessCollector;
import io.pivotal.pa.phoenix.datacollector.uaa.model.Process;
import io.pivotal.pa.phoenix.datacollector.uaa.model.ProcessResponse;
import io.pivotal.pa.phoenix.datacollector.uaa.service.ProcessClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessCollectorImpl implements ProcessCollector {

    @Autowired
    private ProcessClient processClient;

    @Autowired
    private AggregationChannel<Process> channel;


    @Override
    public void collectAndSend(String uaaProcessUrl) {
        ProcessResponse response = processClient.process(uaaProcessUrl);
        channel.send(response.getProcesses());
        if (response.hasNextPage()) {
            collectAndSend(response.getNextPageUri());
        }

    }


}
