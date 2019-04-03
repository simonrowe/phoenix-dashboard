package io.pivotal.pa.phoenix.injestion.service;


import io.pivotal.pa.phoenix.injestion.dao.ApplicationInstanceDao;
import io.pivotal.pa.phoenix.model.ApplicationInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AIServiceTest {


    @Mock
    private ApplicationInstanceDao aiDao;

    @InjectMocks
    private AIService aiService;

    @Test
    public void testPersist() {
        List<ApplicationInstance> toSave = Arrays.asList(
                new ApplicationInstance(null, "abc123", 4, "foundation1", null),
                new ApplicationInstance(null, "abc123", 4, "foundation2", null)
        );
        aiService.save(toSave);
        verify(aiDao, times(1)).saveAll(eq(toSave));

    }

}
