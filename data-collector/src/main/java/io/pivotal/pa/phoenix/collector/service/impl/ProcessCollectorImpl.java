package io.pivotal.pa.phoenix.collector.service.impl;

import io.pivotal.pa.phoenix.collector.service.AggregationChannel;
import io.pivotal.pa.phoenix.collector.service.ProcessCollector;
import io.pivotal.pa.phoenix.collector.capi.model.Process;
import io.pivotal.pa.phoenix.collector.capi.model.ProcessResponse;
import io.pivotal.pa.phoenix.collector.capi.service.ProcessClient;
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
