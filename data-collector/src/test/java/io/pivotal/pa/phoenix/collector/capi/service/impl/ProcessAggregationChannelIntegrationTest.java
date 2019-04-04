package io.pivotal.pa.phoenix.collector.capi.service.impl;

import io.pivotal.pa.phoenix.collector.capi.model.Process;
import io.pivotal.pa.phoenix.collector.service.impl.ProcessAggregationChannel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureStubRunner(ids = "io.pivotal.pa.phoenix:data-ingestor:+:10002", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class ProcessAggregationChannelIntegrationTest {


    @Autowired
    private ProcessAggregationChannel channel;


    @Test
    public void testSendWithValidData() {
        channel.send(Arrays.asList(new Process("abc123", 1),
                new Process("def456",3)));
    }

}
