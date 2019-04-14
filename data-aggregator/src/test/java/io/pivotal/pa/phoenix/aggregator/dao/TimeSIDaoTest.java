package io.pivotal.pa.phoenix.aggregator.dao;


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
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)

public class TimeSIDaoTest {

    @Autowired
    private TimeSIDao timeSIDao;

    @Autowired
    private ServiceInstanceDao serviceInstanceDao;

    @Before
    @After
    public void before() {
        serviceInstanceDao.deleteAll();
    }

    @Test
    public void testInsertTime() {
        Time time = new Time(new Date());
        Time saved = timeSIDao.save(time);
        assertNotNull(saved);
        Optional<Time> retrieved = timeSIDao.findById(saved.getId());
        assertTrue(retrieved.isPresent());
        assertNotNull(retrieved.get().getId());
        assertNotNull(retrieved.get().getTime());
    }

    @Test
    public void testAssociateTimeWithApplicationInstances() {
        //set up the time record
        Time time = new Time(new Date());
        Time saved = timeSIDao.save(time);
        assertNotNull(saved);
        Time retrieved = timeSIDao.findById(saved.getId()).get();

        //set up the SI records
        List<ServiceInstance> sisToPersist = Arrays.asList(
                new ServiceInstance(null, "abc123", "rabbit", "foundation1", null),
                new ServiceInstance(null, "def123", "redis", "foundation1", null),
                new ServiceInstance(null, "abc123", "mysql", "foundation2", null)
        );

        serviceInstanceDao.saveAll(sisToPersist);

        assertThat(timeSIDao.findSIsByTime(retrieved).size(), is(0));

        timeSIDao.associateTime(retrieved);

        assertThat(timeSIDao.findSIsByTime(retrieved).size(), is(3));

    }


}
