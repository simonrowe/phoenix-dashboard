package io.pivotal.pa.phoenix.web.dao;

import io.pivotal.pa.phoenix.model.AggregatedSI;
import io.pivotal.pa.phoenix.model.Time;
import org.junit.After;
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
public class AggregatedSIDaoTest {

    @Autowired
    private AggregatedSiDao aggregatedSiDao;

    @Autowired
    private TimeDao timeDao;


    @Before
    public void before() {
        aggregatedSiDao.deleteAll();
        timeDao.deleteAll();
    }

    @After
    public void after() {
        aggregatedSiDao.deleteAll();
        timeDao.deleteAll();
    }

    @Test
    public void testFindMaxSIs() {
        aggregatedSiDao.saveAll(Arrays.asList(new AggregatedSI(9L), new AggregatedSI(19L), new AggregatedSI(5L)));
        assertThat(aggregatedSiDao.findMaxSIs().getCount(), is(19));

    }

    @Test
    public void testFindMaxSIsFromPointInTime() throws Exception {
        Time firstJan2019 = timeDao.save(new Time(new SimpleDateFormat("dd-MMM-yyyy").parse("01-Jan-2019")));
        Time firstMarch2019 = timeDao.save(new Time(new SimpleDateFormat("dd-MMM-yyyy").parse("01-Mar-2019")));
        aggregatedSiDao.saveAll(Arrays.asList(new AggregatedSI(null,1000, firstJan2019),
                new AggregatedSI(null,800, firstMarch2019)));
        assertThat(aggregatedSiDao.findMaxSIsAfter(new SimpleDateFormat("dd-MMM-yyyy").parse("01-Jan-2019")).getCount(), is(1000));
        assertThat(aggregatedSiDao.findMaxSIsAfter(new SimpleDateFormat("dd-MMM-yyyy").parse("01-Feb-2019")).getCount(), is(800));
    }

    @Test
    public void testLatestSICount() throws Exception {
        Time firstJan2019 = timeDao.save(new Time(new SimpleDateFormat("dd-MMM-yyyy").parse("01-Jan-2019")));
        Time firstMarch2019 = timeDao.save(new Time(new SimpleDateFormat("dd-MMM-yyyy").parse("01-Mar-2019")));
        aggregatedSiDao.saveAll(Arrays.asList(new AggregatedSI(null,1000, firstJan2019),
                new AggregatedSI(null,800, firstMarch2019)));
        AggregatedSI aggregatedSI = aggregatedSiDao.latestSICount();
        assertThat(aggregatedSI.getSiCount(), is(800));
        assertThat(aggregatedSI.getTime().getId(), is(firstMarch2019.getId()));
    }



}
