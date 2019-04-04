package io.pivotal.pa.phoenix.aggregator.dao;


import io.pivotal.pa.phoenix.model.AggregatedSI;
import io.pivotal.pa.phoenix.model.ServiceInstance;
import io.pivotal.pa.phoenix.model.Time;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AggregatedSIDaoTest {

    @Autowired
    private AggregatedSiDao aggregatedSiDao;

    @Autowired
    private TimeDao timeDao;

    @Autowired
    private ServiceInstanceDao serviceInstanceDao;

    @Before
    public void before() {
        aggregatedSiDao.deleteAll();
        timeDao.deleteAll();
        serviceInstanceDao.deleteAll();
    }

    @Test
    public void testAggregation() {
        //set up the time record
        Time time = new Time(new Date());
        Time saved = timeDao.save(time);
        assertNotNull(saved);
        Time retrieved = timeDao.findById(saved.getId()).get();

        List<ServiceInstance> sisToPersist = Arrays.asList(
                new ServiceInstance(null, "abc123", "mysql", "foundation1", null),
                new ServiceInstance(null, "def123", "redis", "foundation1", null),
                new ServiceInstance(null, "abc123", "rabbit", "foundation2", null)
        );

        serviceInstanceDao.saveAll(sisToPersist);
        timeDao.associateTime(retrieved);
        assertThat(aggregatedSiDao.count(), is(0l));
        AggregatedSI aggregatedSi = aggregatedSiDao.aggregate(retrieved);
        aggregatedSi.setTime(retrieved);
        aggregatedSiDao.save(aggregatedSi);
        assertThat(aggregatedSiDao.findByTime(retrieved).getSiCount(), is(9));
    }
}
