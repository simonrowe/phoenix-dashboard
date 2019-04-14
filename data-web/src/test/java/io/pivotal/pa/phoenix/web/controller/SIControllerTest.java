package io.pivotal.pa.phoenix.web.controller;

import io.pivotal.pa.phoenix.model.AggregatedSI;
import io.pivotal.pa.phoenix.model.Time;
import io.pivotal.pa.phoenix.web.dao.AggregatedSiDao;
import io.pivotal.pa.phoenix.web.model.CountDto;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = SIController.class)
public class SIControllerTest {

    @MockBean
    private AggregatedSiDao aggregatedSiDao;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testSis() throws Exception {
        CountDto countDto = new CountDto(10);
        given(aggregatedSiDao.findMaxSIs()).willReturn(countDto);
        mockMvc.perform(get("/api/sis/max"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(is(10)));
    }

    @Test
    public void testSisAfterDate() throws Exception {
        CountDto countDto = new CountDto(100);
        ArgumentCaptor<Date> argumentCaptor = ArgumentCaptor.forClass(Date.class);
        given(aggregatedSiDao.findMaxSIsAfter(argumentCaptor.capture())).willReturn(countDto);
        mockMvc.perform(get("/api/sis/max?from=01-Dec-1999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(is(100)));
        assertTrue(DateUtils.isSameDay(argumentCaptor.getValue(), new SimpleDateFormat("dd-MMM-yyyy").parse("01-Dec-1999")));
    }

    @Test
    public void testSummary() throws Exception {
        given(aggregatedSiDao.latestSICount()).willReturn(
                new AggregatedSI(1L, 1567, new Time(new SimpleDateFormat("dd-MMM-yyyy").parse("01-Dec-1999"))));
        CountDto countDto = new CountDto(2000);
        given(aggregatedSiDao.findMaxSIs()).willReturn(countDto);
        mockMvc.perform(get("/api/sis/summary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentCount").value(is(1567)))
                .andExpect(jsonPath("$.lastReadDate").value(is("01-Dec-1999 00:00")))
                .andExpect(jsonPath("$.maxCount").value(is(2000)));

    }

}
