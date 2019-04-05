package io.pivotal.pa.phoenix.aggregator.dao;


import io.pivotal.pa.phoenix.model.AggregatedSI;
import io.pivotal.pa.phoenix.model.ServiceInstance;
import io.pivotal.pa.phoenix.model.Time;
import org.junit.After;
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
    private TimeSIDao timeSIDao;

    @Autowired
    private ServiceInstanceDao serviceInstanceDao;

    @Before
    @After
    public void before() {
        aggregatedSiDao.deleteAll();
        serviceInstanceDao.deleteAll();
        timeSIDao.deleteAll();
    }

    @Test
    public void testAggregation() {
        //set up the time record
        Time time = new Time(new Date());
        Time saved = timeSIDao.save(time);
        assertNotNull(saved);
        Time retrieved = timeSIDao.findById(saved.getId()).get();

        List<ServiceInstance> sisToPersist = Arrays.asList(
                new ServiceInstance(null, "abc123", "mysql", "foundation1", null),
                new ServiceInstance(null, "def123", "redis", "foundation1", null),
                new ServiceInstance(null, "abc123", "rabbit", "foundation2", null)
        );

        serviceInstanceDao.saveAll(sisToPersist);
        timeSIDao.associateTime(retrieved);
        AggregatedSI aggregatedSi = aggregatedSiDao.aggregate(retrieved);
        aggregatedSi.setTime(retrieved);
        aggregatedSiDao.save(aggregatedSi);
        assertThat(aggregatedSiDao.findByTime(retrieved).getSiCount(), is(3));
    }
}
