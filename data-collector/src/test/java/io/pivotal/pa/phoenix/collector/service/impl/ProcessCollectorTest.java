package io.pivotal.pa.phoenix.collector.service.impl;

import io.pivotal.pa.phoenix.collector.capi.model.Href;
import io.pivotal.pa.phoenix.collector.capi.model.Pagination;
import io.pivotal.pa.phoenix.collector.capi.model.Process;
import io.pivotal.pa.phoenix.collector.capi.model.ProcessCloudControllerResponse;
import io.pivotal.pa.phoenix.collector.capi.service.impl.ProcessClientImpl;
import io.pivotal.pa.phoenix.collector.service.AggregationChannel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProcessCollectorTest {

    public static final String HTTP_UAA_NEXT = "http://capi/next";
    @Mock
    private ProcessClientImpl client;

    private String uaaProcessUrl = "http://capi/";

    @Mock
    private AggregationChannel<Process> processAggregationChannel;

    @InjectMocks
    private ProcessCollectorImpl processCollector;


    @Test
    public void testCollectWith2Pages() {
        given(client.process(uaaProcessUrl)).willReturn(page1Process());
        given(client.process(HTTP_UAA_NEXT)).willReturn(lastPageResponse());

        processCollector.collectAndSend(uaaProcessUrl);

        verify(client, times(1)).process(uaaProcessUrl);
        verify(client, times(1)).process(HTTP_UAA_NEXT);

        ArgumentCaptor<List<Process>> lastArgumentCapture = ArgumentCaptor.forClass(List.class);
        verify(processAggregationChannel, times(2)).send(lastArgumentCapture.capture());

        List<Process> processesInFirstCall = lastArgumentCapture.getValue();
        assertThat(1, is(processesInFirstCall.size()));
        assertThat("efg890", is(processesInFirstCall.get(0).getGuid()));
        assertThat(5, is(processesInFirstCall.get(0).getInstances()));
    }

    private ProcessCloudControllerResponse lastPageResponse() {
        Pagination pagination = new Pagination();
        pagination.setNext(null);
        ProcessCloudControllerResponse processResponse = new ProcessCloudControllerResponse();
        processResponse.setPagination(pagination);
        processResponse.setProcesses(Arrays.asList(
                new Process("efg890", 5)
        ));
        return processResponse;
    }

    private ProcessCloudControllerResponse page1Process() {
        Pagination pagination = new Pagination();
        pagination.setNext(new Href(HTTP_UAA_NEXT));
        ProcessCloudControllerResponse processResponse = new ProcessCloudControllerResponse();
        processResponse.setPagination(pagination);
        processResponse.setProcesses(Arrays.asList(
                new Process("abc123", 2),
                new Process("def456", 1)
        ));
        return processResponse;
    }


}
