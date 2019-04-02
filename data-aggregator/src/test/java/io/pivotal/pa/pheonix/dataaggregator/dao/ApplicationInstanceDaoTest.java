package io.pivotal.pa.pheonix.dataaggregator.dao;

import io.pivotal.pa.pheonix.dataaggregator.model.ApplicationInstance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationInstanceDaoTest {

    @Autowired
    private ApplicationInstanceDao applicationInstanceDao;


    @Before
    public void before() {
        applicationInstanceDao.deleteAll();
    }


    @Test
    public void testPersist() {
        List<ApplicationInstance> aisToPersist = Arrays.asList(
                new ApplicationInstance(null, "abc123", 3, "foundation1", null),
                new ApplicationInstance(null, "def123", 1, "foundation1", null),
                new ApplicationInstance(null, "abc123", 5, "foundation2", null)
        );
        applicationInstanceDao.saveAll(aisToPersist);
        assertThat(applicationInstanceDao.count(), is(3L));
        List<ApplicationInstance> ais = (List<ApplicationInstance>) applicationInstanceDao.findAll();
        assertNotNull(ais.get(0).getId());
        assertThat(ais.get(0).getAppGuidId(), is("abc123"));
        assertThat(ais.get(0).getInstances(), is(3));
        assertThat(ais.get(0).getFoundationId(), is("foundation1"));
        assertThat(ais.get(2).getAppGuidId(), is("abc123"));
        assertThat(ais.get(2).getInstances(), is(5));
        assertThat(ais.get(2).getFoundationId(), is("foundation2"));
    }




}
