package io.pivotal.pa.phoenix.aggregator.service;


import io.pivotal.pa.phoenix.aggregator.dao.AggregatedSiDao;
import io.pivotal.pa.phoenix.aggregator.dao.TimeDao;
import io.pivotal.pa.phoenix.model.AggregatedAI;
import io.pivotal.pa.phoenix.model.Time;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SIAggregationServiceTest {

    @Mock
    private TimeDao timeDao;

    @Mock
    private AggregatedSiDao aggregatedAiDao;


    @InjectMocks
    private AIAggregationService aggregationScheduler;

    @Test
    public void testSchedule() {
        Time time = new Time(new Date());
        ArgumentCaptor<Time> timeArgumentCaptor = ArgumentCaptor.forClass(Time.class);
        when(timeDao.save(timeArgumentCaptor.capture())).thenReturn(time);
        AggregatedAI aggregatedAI = new AggregatedAI();
        when(aggregatedAiDao.aggregate(isA(Time.class))).thenReturn(aggregatedAI);
        aggregationScheduler.aggregate();

        verify(timeDao, times(1)).associateTime(time);
        assertNotNull(timeArgumentCaptor.getValue().getTime());

        assertThat(aggregatedAI.getTime(), is(time));
        verify(aggregatedAiDao, times(1)).save(aggregatedAI);
    }

}
