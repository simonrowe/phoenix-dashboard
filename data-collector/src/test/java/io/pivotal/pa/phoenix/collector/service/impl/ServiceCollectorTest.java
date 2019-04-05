package io.pivotal.pa.phoenix.collector.service.impl;

import io.pivotal.pa.phoenix.collector.capi.model.*;
import io.pivotal.pa.phoenix.collector.capi.service.impl.ServiceClientImpl;
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
public class ServiceCollectorTest {

    public static final String HTTP_UAA_NEXT = "http://capi/next";

    @Mock
    private ServiceClientImpl client;

    private String uaaProcessUrl = "http://capi/";

    @Mock
    private AggregationChannel<Services> serviceAggregationChannel;

    @InjectMocks
    private ServiceCollectorImpl processCollector;


    @Test
    public void testCollectWith2Pages() {
        given(client.process(uaaProcessUrl)).willReturn(page1Service());
        given(client.process(HTTP_UAA_NEXT)).willReturn(lastPageResponse());

        processCollector.collectAndSend(uaaProcessUrl);

        verify(client, times(1)).process(uaaProcessUrl);
        verify(client, times(1)).process(HTTP_UAA_NEXT);

        ArgumentCaptor<List<Services>> lastArgumentCapture = ArgumentCaptor.forClass(List.class);
        verify(serviceAggregationChannel, times(2)).send(lastArgumentCapture.capture());

        List<Services> servicesInFirstCall = lastArgumentCapture.getValue();
        assertThat(1, is(servicesInFirstCall.size()));
        assertThat("efg890", is(servicesInFirstCall.get(0).getGuid()));
        assertThat("redis", is(servicesInFirstCall.get(0).getName()));
    }

    private ServiceCloudControllerResponse lastPageResponse() {
        Pagination pagination = new Pagination();
        pagination.setNext(null);
        ServiceCloudControllerResponse serviceResponse = new ServiceCloudControllerResponse();
        serviceResponse.setPagination(pagination);
        serviceResponse.setServices(Arrays.asList(
                new Services("efg890", "redis")
        ));
        return serviceResponse;
    }

    private ServiceCloudControllerResponse page1Service() {
        Pagination pagination = new Pagination();
        pagination.setNext(new Href(HTTP_UAA_NEXT));
        ServiceCloudControllerResponse serviceResponse = new ServiceCloudControllerResponse();
        serviceResponse.setPagination(pagination);
        serviceResponse.setServices(Arrays.asList(
                new Services("abc123", "redis"),
                new Services("def456", "mysql")
        ));
        return serviceResponse;
    }


}
