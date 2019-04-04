package io.pivotal.pa.phoenix.collector.capi.service.impl;

import io.pivotal.pa.phoenix.collector.capi.model.ProcessResponse;
import io.pivotal.pa.phoenix.collector.capi.service.CloudControllerClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = "io.pivotal.pa.phoenix:capi-stubs:+:10001", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class ProcessClientTest {

    @Autowired
    private CloudControllerClient<ProcessResponse> client;

    @Test
    public void testProcessFirstPage() throws Exception {
        ProcessResponse response = client.process("http://localhost:10001/v3/processes?page=1&per_page=2");
        assertNotNull(response);
        assertThat(response.getNextPageUri(), is("http://localhost:10001/v3/processes?page=2&per_page=2"));
        assertThat(response.getProcesses().size(), is(2));
        assertThat(response.getProcesses().get(0).getGuid(), is ("abc123"));
        assertThat(response.getProcesses().get(0).getInstances(), is(1));
        assertThat(response.getProcesses().get(1).getGuid(), is ("abc567"));
        assertThat(response.getProcesses().get(1).getInstances(), is(3));
    }

    @Test
    public void testProcessSecondPage() throws Exception {
        ProcessResponse response = client.process("http://localhost:10001/v3/processes?page=2&per_page=2");
        assertNotNull(response);
        assertNull(response.getNextPageUri());
        assertThat(response.getProcesses().size(), is(1));
        assertThat(response.getProcesses().get(0).getGuid(), is ("def456"));
        assertThat(response.getProcesses().get(0).getInstances(), is(1));
    }
}