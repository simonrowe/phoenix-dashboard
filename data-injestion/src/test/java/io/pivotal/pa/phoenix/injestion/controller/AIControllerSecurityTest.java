package io.pivotal.pa.phoenix.injestion.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.pivotal.pa.phoenix.model.ApplicationInstance;
import io.pivotal.pa.phoenix.injestion.service.AIService;
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

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AIController.class)
public class AIControllerSecurityTest {

    @MockBean
    private AIService aiService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    //testPost with no user
    @Test
    public void testPostWithNoAuthentication() throws Exception {
        mockMvc.perform(post("/api/ai/receive").
                content(objectMapper.writeValueAsString(Arrays.asList(
                        new ApplicationInstance(null, "abc123", 3, "foundation1", null),
                        new ApplicationInstance(null, "def123", 1, "foundation1", null),
                        new ApplicationInstance(null, "abc123", 5, "foundation2", null)
                ))).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isUnauthorized());

        ArgumentCaptor<List> argumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(aiService, never()).save(argumentCaptor.capture());
    }
}
