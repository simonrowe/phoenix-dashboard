package io.pivotal.pa.phoenix.dataaggregator.dao;

import io.pivotal.pa.phoenix.dataaggregator.model.ApplicationInstance;
import io.pivotal.pa.phoenix.dataaggregator.model.Time;
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TimeDaoTest {

    @Autowired
    private TimeDao timeDao;

    @Autowired
    private ApplicationInstanceDao applicationInstanceDao;

    @Before
    public void before() {
        applicationInstanceDao.deleteAll();
    }

    @Test
    public void testInsertTime() {
        Time time = new Time(new Date());
        Time saved = timeDao.save(time);
        assertNotNull(saved);
        Optional<Time> retrieved = timeDao.findById(saved.getId());
        assertTrue(retrieved.isPresent());
        assertNotNull(retrieved.get().getId());
        assertNotNull(retrieved.get().getTime());
    }

    @Test
    public void testAssociateTimeWithApplicationInstances() {
        //set up the time record
        Time time = new Time(new Date());
        Time saved = timeDao.save(time);
        assertNotNull(saved);
        Time retrieved = timeDao.findById(saved.getId()).get();

        //set up the AI records
        List<ApplicationInstance> aisToPersist = Arrays.asList(
                new ApplicationInstance(null, "abc123", 3, "foundation1", null),
                new ApplicationInstance(null, "def123", 1, "foundation1", null),
                new ApplicationInstance(null, "abc123", 5, "foundation2", null)
        );

        applicationInstanceDao.saveAll(aisToPersist);

        assertThat(applicationInstanceDao.findByTime(retrieved).size(), is(0));

        timeDao.associateTime(retrieved);

        assertThat(applicationInstanceDao.findByTime(retrieved).size(), is(3));

    }



}
