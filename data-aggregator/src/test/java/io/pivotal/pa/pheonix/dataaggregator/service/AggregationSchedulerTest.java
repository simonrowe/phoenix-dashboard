package io.pivotal.pa.pheonix.dataaggregator.service;


import io.pivotal.pa.pheonix.dataaggregator.dao.AggregatedAiDao;
import io.pivotal.pa.pheonix.dataaggregator.dao.TimeDao;
import io.pivotal.pa.pheonix.dataaggregator.model.Time;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AggregationSchedulerTest {

    @Mock
    private TimeDao timeDao;

    @Mock
    private AggregatedAiDao aggregatedAiDao;


    @InjectMocks
    private AggregationScheduler aggregationScheduler;

    @Test
    public void testSchedule() {
        Time time = new Time(new Date());
        ArgumentCaptor<Time> timeArgumentCaptor = ArgumentCaptor.forClass(Time.class);
        when(timeDao.save(timeArgumentCaptor.capture())).thenReturn(time);

        aggregationScheduler.aggregate();

        verify(timeDao, times(1)).associateTime(time);
        assertNotNull(timeArgumentCaptor.getValue().getTime());
        verify(aggregatedAiDao, times(1)).aggregate(time);

    }

}
