package io.pivotal.pa.phoenix.collector.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CapiUriBuilderTest {

    @Autowired
    @Qualifier("processUriBuilder")
    private CapiUriBuilder processUriBuilder;


    @Test
    public void testBuild() {
        assertThat(processUriBuilder.build(), is("http://localhost:10001/v3/processes?page=1&per_page=2"));
    }


}
