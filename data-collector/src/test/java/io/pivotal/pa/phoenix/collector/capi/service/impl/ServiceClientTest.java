package io.pivotal.pa.phoenix.collector.capi.service.impl;

import io.pivotal.pa.phoenix.collector.capi.model.ServiceResponse;
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
public class ServiceClientTest {
    @Autowired
    private CloudControllerClient<ServiceResponse> client;

    @Test
    public void testProcessFirstPage() throws Exception {
        ServiceResponse response = client.process("http://localhost:10001/v3/service_instances?page=1&per_page=2");
        assertNotNull(response);
        assertThat(response.getNextPageUri(), is("http://localhost:10001/v3/service_instances?page=2&per_page=2"));
        assertThat(response.getServices().size(), is(2));
        assertThat(response.getServices().get(0).getGuid(), is ("abc123"));
        assertThat(response.getServices().get(0).getName(), is ("redis"));
        //assertThat(response.getServices().get(0).getInstances(), is(1));
        assertThat(response.getServices().get(1).getGuid(), is ("abc567"));
        assertThat(response.getServices().get(1).getName(), is ("mysql"));
        //assertThat(response.getProcesses().get(1).getInstances(), is(3));
    }

    @Test
    public void testProcessSecondPage() throws Exception {
        ServiceResponse response = client.process("http://localhost:10001/v3/service_instances?page=2&per_page=2");
        assertNotNull(response);
        assertNull(response.getNextPageUri());
        assertThat(response.getServices().size(), is(1));
        assertThat(response.getServices().get(0).getGuid(), is ("def456"));
        assertThat(response.getServices().get(0).getName(), is ("redis"));
    }
}
