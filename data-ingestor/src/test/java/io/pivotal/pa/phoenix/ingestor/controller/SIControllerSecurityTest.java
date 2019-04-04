package io.pivotal.pa.phoenix.ingestor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.pivotal.pa.phoenix.ingestor.service.SIService;
import io.pivotal.pa.phoenix.model.ServiceInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.verify;
import static org.mockito.Mockito.never;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = SIController.class)
public class SIControllerSecurityTest {

    @MockBean
    private SIService siService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    //testPost with no user
    @Test
    public void testPostWithNoAuthentication() throws Exception {
        mockMvc.perform(post("/api/si/receive").
                content(objectMapper.writeValueAsString(Arrays.asList(
                        new ServiceInstance(null, "abc123", "mysql", "foundation1", null),
                        new ServiceInstance(null, "def123", "redis", "foundation1", null),
                        new ServiceInstance(null, "abc123", "rabbit", "foundation2", null)
                ))).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isUnauthorized());

        ArgumentCaptor<List> argumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(siService, never()).save(argumentCaptor.capture());
    }
}
