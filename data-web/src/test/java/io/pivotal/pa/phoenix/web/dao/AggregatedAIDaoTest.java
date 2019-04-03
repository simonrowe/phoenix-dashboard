package io.pivotal.pa.phoenix.web.dao;

import io.pivotal.pa.phoenix.model.AggregatedAI;
import io.pivotal.pa.phoenix.model.Time;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AggregatedAIDaoTest {

    @Autowired
    private AggregatedAiDao aggregatedAiDao;

    @Autowired
    private TimeDao timeDao;


    @Before
    public void before() {
        aggregatedAiDao.deleteAll();
        timeDao.deleteAll();
    }


    @Test
    public void testFindMaxAIs() {
        aggregatedAiDao.saveAll(Arrays.asList(new AggregatedAI(9L),
                new AggregatedAI(19L),
                new AggregatedAI(5L)));
        assertThat(aggregatedAiDao.findMaxAIs().getCount(), is(19));

    }

    @Test
    public void testFindMaxAIsFromPointInTime() throws Exception {
        Time firstJan2019 = timeDao.save(new Time(new SimpleDateFormat("dd-MMM-yyyy").parse("01-Jan-2019")));
        Time firstMarch2019 = timeDao.save(new Time(new SimpleDateFormat("dd-MMM-yyyy").parse("01-Mar-2019")));
        aggregatedAiDao.saveAll(Arrays.asList(new AggregatedAI(null,1000, firstJan2019),
                new AggregatedAI(null,800, firstMarch2019)));
        assertThat(aggregatedAiDao.findMaxAIsAfter(new SimpleDateFormat("dd-MMM-yyyy").parse("01-Jan-2019")).getCount(), is(1000));
        assertThat(aggregatedAiDao.findMaxAIsAfter(new SimpleDateFormat("dd-MMM-yyyy").parse("01-Feb-2019")).getCount(), is(800));
    }

    @Test
    public void testLatestAICount() throws Exception {
        Time firstJan2019 = timeDao.save(new Time(new SimpleDateFormat("dd-MMM-yyyy").parse("01-Jan-2019")));
        Time firstMarch2019 = timeDao.save(new Time(new SimpleDateFormat("dd-MMM-yyyy").parse("01-Mar-2019")));
        aggregatedAiDao.saveAll(Arrays.asList(new AggregatedAI(null,1000, firstJan2019),
                new AggregatedAI(null,800, firstMarch2019)));
        AggregatedAI aggregatedAI = aggregatedAiDao.latestAICount();
        assertThat(aggregatedAI.getAiCount(), is(800));
        assertThat(aggregatedAI.getTime().getId(), is(firstMarch2019.getId()));
    }



}
