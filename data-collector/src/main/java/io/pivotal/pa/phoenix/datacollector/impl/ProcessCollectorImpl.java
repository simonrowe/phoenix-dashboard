package io.pivotal.pa.phoenix.datacollector.impl;

import io.pivotal.pa.phoenix.datacollector.AggregationChannel;
import io.pivotal.pa.phoenix.datacollector.ProcessCollector;
import io.pivotal.pa.phoenix.datacollector.uaa.ProcessClient;
import io.pivotal.pa.phoenix.datacollector.uaa.model.Process;
import io.pivotal.pa.phoenix.datacollector.uaa.model.ProcessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class ProcessCollectorImpl implements ProcessCollector {

    @Autowired
    private ProcessClient processClient;

    @Value("${uaa.uri}/v3/processes?page=1&per_page=${uaa.pageSize}")
    private String uaaProcessUrl;

    @Autowired
    private AggregationChannel<Process> channel;



    @Override
    public void collectAndSend() {
        collectAndSend(uaaProcessUrl);
    }

    @Override
    public void collectAndSend(String uaaProcessUrl) {
        ProcessResponse response = processClient.process(uaaProcessUrl);
        channel.send(response.getProcesses());
        if (response.hasNextPage()) {
            collectAndSend(response.getNextPageUri());
        }

    }


}
