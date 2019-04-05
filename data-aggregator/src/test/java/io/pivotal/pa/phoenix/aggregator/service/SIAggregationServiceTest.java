package io.pivotal.pa.phoenix.aggregator.service;


import io.pivotal.pa.phoenix.aggregator.dao.AggregatedSiDao;
import io.pivotal.pa.phoenix.aggregator.dao.TimeSIDao;
import io.pivotal.pa.phoenix.model.AggregatedSI;
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
    private TimeSIDao timeSIDao;

    @Mock
    private AggregatedSiDao aggregatedSiDao;


    @InjectMocks
    private SIAggregationService aggregationScheduler;

    @Test
    public void testSchedule() {
        Time time = new Time(new Date());
        ArgumentCaptor<Time> timeArgumentCaptor = ArgumentCaptor.forClass(Time.class);
        when(timeSIDao.save(timeArgumentCaptor.capture())).thenReturn(time);
        AggregatedSI aggregatedSI = new AggregatedSI();
        when(aggregatedSiDao.aggregate(isA(Time.class))).thenReturn(aggregatedSI);
        aggregationScheduler.aggregate();

        verify(timeSIDao, times(1)).associateTime(time);
        assertNotNull(timeArgumentCaptor.getValue().getTime());

        assertThat(aggregatedSI.getTime(), is(time));
        verify(aggregatedSiDao, times(1)).save(aggregatedSI);
    }

}
