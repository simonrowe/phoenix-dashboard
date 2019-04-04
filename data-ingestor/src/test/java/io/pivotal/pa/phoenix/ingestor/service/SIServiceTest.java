package io.pivotal.pa.phoenix.ingestor.service;


import io.pivotal.pa.phoenix.ingestor.dao.ServiceInstanceDao;
import io.pivotal.pa.phoenix.model.ServiceInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SIServiceTest {


    @Mock
    private ServiceInstanceDao aiDao;

    @InjectMocks
    private SIService siService;

    @Test
    public void testPersist() {
        List<ServiceInstance> toSave = Arrays.asList(
                new ServiceInstance(null, "abc123", "mysql", "foundation1", null),
                new ServiceInstance(null, "abc123", "rabbit", "foundation2", null)
        );
        siService.save(toSave);
        verify(aiDao, times(1)).saveAll(eq(toSave));

    }

}
