package io.pivotal.pa.phoenix.web.controller;

import io.pivotal.pa.phoenix.web.dao.AggregatedAiDao;
import io.pivotal.pa.phoenix.web.model.CountDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AIController.class)
public class AIControllerTest {

    @MockBean
    private AggregatedAiDao aggregatedAiDao;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testPost() throws Exception {

        CountDto countDto = new CountDto(10);
        given(aggregatedAiDao.findMaxAIs()).willReturn(countDto);
        mockMvc.perform(get("/api/ais/max"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(is(10)));

    }

}
