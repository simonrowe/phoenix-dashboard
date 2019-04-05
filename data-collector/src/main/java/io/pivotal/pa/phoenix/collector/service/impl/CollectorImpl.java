package io.pivotal.pa.phoenix.collector.service.impl;

import io.pivotal.pa.phoenix.collector.capi.model.AbstractCloudControllerResponse;
import io.pivotal.pa.phoenix.collector.capi.model.Process;
import io.pivotal.pa.phoenix.collector.capi.service.impl.CloudControllerClient;
import io.pivotal.pa.phoenix.collector.service.AggregationChannel;
import org.springframework.beans.factory.annotation.Autowired;

public class CollectorImpl {

    @Autowired
    private CloudControllerClient cloudControllerClient;

    @Autowired
    private AggregationChannel<Process> channel;

    public <T extends AbstractCloudControllerResponse> void collectAndSend(String cloudControllerUri,
                                                                           Class<T> cloudControllerResponseClass,
                                                                           Str
                                                                           ) {
        T response = cloudControllerClient.process(cloudControllerUri, cloudControllerResponseClass);
        channel.send(response.getResources());
        if (response.hasNextPage()) {
            //collectAndSend(response.getNextPageUri());
        }
    }
}
