package io.pivotal.pa.phoenix.web.dao;

import io.pivotal.pa.phoenix.model.AggregatedAI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AggregatedAIDaoTest {

    @Autowired
    private AggregatedAiDao aggregatedAiDao;


    @Before
    public void before() {
        aggregatedAiDao.deleteAll();
    }


    @Test
    public void testFindMaxAIs() {
        aggregatedAiDao.saveAll(Arrays.asList(new AggregatedAI(9L),
                new AggregatedAI(19L),
                new AggregatedAI(5L)));
        assertThat(aggregatedAiDao.findMaxAIs().getCount(), is(19));

    }
}
