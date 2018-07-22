package com.welt.data.controller;

import com.welt.data.Application;
import com.welt.data.controller.errors.ApplicationExceptionHandler;
import com.welt.data.service.UserDataService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserDataControllerIntegrationTest {

    @Autowired
    UserDataController userDataController;

    @Autowired
    UserDataService userDataService;

    @Autowired
    private ApplicationExceptionHandler applicationExceptionHandler;

    private MockMvc userDataMockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.userDataMockMvc = MockMvcBuilders.standaloneSetup(userDataController)
                .setControllerAdvice(applicationExceptionHandler).build();
    }

    @Test
    public void testFetchUserDataSucceedsForValidUserId() throws Exception {

        userDataMockMvc.perform(get("/api/v1/users/1/data"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(1))
                .andExpect(jsonPath("$.posts").exists());
    }

    @Test
    public void testFetchUserDataFailsForInvalidUserId() throws Exception {

        userDataMockMvc.perform(get("/api/v1/users/100000000/data"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("404 Not Found"));
    }

    @Test
    public void testFetchUserDataFailsForInvalidHttpMethod() throws Exception {

        userDataMockMvc.perform(post("/api/v1/users/1/data"))
                .andExpect(status().isMethodNotAllowed());
    }
}
