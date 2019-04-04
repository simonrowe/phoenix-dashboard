package io.pivotal.pa.phoenix.ingestor.dao;

import io.pivotal.pa.phoenix.model.ApplicationInstance;
import io.pivotal.pa.phoenix.model.ServiceInstance;
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
public class ServiceInstanceDaoTest {
    @Autowired
    private ServiceInstanceDao serviceInstanceDao;


    @Before
    public void before() {
        serviceInstanceDao.deleteAll();
    }


    @Test
    public void testPersist() {
        List<ServiceInstance> sisToPersist = Arrays.asList(
                new ServiceInstance(null, "abc123", "mysql", "foundation1", null),
                new ServiceInstance(null, "def123", "redis", "foundation1", null),
                new ServiceInstance(null, "abc123", "rabbit", "foundation2", null)
        );
        serviceInstanceDao.saveAll(sisToPersist);
        assertThat(serviceInstanceDao.count(), is(3L));
        List<ServiceInstance> sis = (List<ServiceInstance>) serviceInstanceDao.findAll();
        assertNotNull(sis.get(0).getId());
        assertThat(sis.get(0).getServiceGuidId(), is("abc123"));
        assertThat(sis.get(0).getServiceName(), is("mysql"));
        assertThat(sis.get(0).getFoundationId(), is("foundation1"));
        assertThat(sis.get(2).getServiceGuidId(), is("abc123"));
        assertThat(sis.get(2).getServiceName(), is("rabbit"));
        assertThat(sis.get(2).getFoundationId(), is("foundation2"));

    }



}
