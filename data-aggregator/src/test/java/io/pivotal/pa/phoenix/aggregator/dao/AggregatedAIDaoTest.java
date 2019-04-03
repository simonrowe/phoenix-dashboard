package io.pivotal.pa.phoenix.aggregator.dao;


import io.pivotal.pa.phoenix.model.AggregatedAI;
import io.pivotal.pa.phoenix.model.ApplicationInstance;
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
public class AggregatedAIDaoTest {

    @Autowired
    private AggregatedAiDao aggregatedAiDao;

    @Autowired
    private TimeDao timeDao;

    @Autowired
    private ApplicationInstanceDao applicationInstanceDao;

    @Before
    public void before() {
        aggregatedAiDao.deleteAll();
        timeDao.deleteAll();
        applicationInstanceDao.deleteAll();
    }

    @Test
    public void testAggregation() {
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
        timeDao.associateTime(retrieved);
        assertThat(aggregatedAiDao.count(), is(0l));
        AggregatedAI aggregatedAi = aggregatedAiDao.aggregate(retrieved);
        aggregatedAi.setTime(retrieved);
        aggregatedAiDao.save(aggregatedAi);
        assertThat(aggregatedAiDao.findByTime(retrieved).getAiCount(), is(9));
    }
}
