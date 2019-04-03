package io.pivotal.pa.phoenix.collector.service.impl;

import io.pivotal.pa.phoenix.collector.capi.service.impl.CapiUriBuilder;
import io.pivotal.pa.phoenix.collector.service.ProcessCollector;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CollectionSchedulerTest {

    @Mock
    private ProcessCollector processCollector;

    @Mock
    private CapiUriBuilder processUriBuilder;

    private CollectionScheduler collectionScheduler = new CollectionScheduler();

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(collectionScheduler, "processCollector", processCollector);
        ReflectionTestUtils.setField(collectionScheduler, "processUriBuilder", processUriBuilder);
    }

    @Test
    public void testSchedule() {
        given(processUriBuilder.build()).willReturn("http://capi");
        collectionScheduler.collect();
        verify(processCollector,times(1)).collectAndSend(eq("http://capi"));
    }

}
