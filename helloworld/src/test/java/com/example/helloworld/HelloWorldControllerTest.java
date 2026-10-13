
package com.example.helloworld;

import com.example.helloworld.controller.HelloWorldController;
import com.example.helloworld.exception.GlobalExceptionHandler;
import com.example.helloworld.exception.InvalidInputException;
import com.example.helloworld.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HelloWorldController.class)
@Import(GlobalExceptionHandler.class)
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private HelloWorldService helloWorldService;

    @Test
    void shouldReturnMessageForNameStartingWithA() throws Exception {
        given(helloWorldService.createMessage("alice"))
                .willReturn("Hello Alice");

        mockMvc.perform(get("/hello-world")
                        .param("name", "alice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message")
                        .value("Hello Alice"));
    }

    @Test
    void shouldReturnMessageForNameStartingWithM() throws Exception {
        given(helloWorldService.createMessage("malar"))
                .willReturn("Hello Malar");

        mockMvc.perform(get("/hello-world")
                        .param("name", "malar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message")
                        .value("Hello Malar"));
    }

    @Test
    void shouldReturnBadRequestForNameStartingWithN() throws Exception {
        willThrow(new InvalidInputException())
                .given(helloWorldService)
                .createMessage("narmatha");

        mockMvc.perform(get("/hello-world")
                        .param("name", "narmatha"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error")
                        .value("Invalid Input"));
    }

    @Test
    void shouldReturnBadRequestForNameStartingWithZ() throws Exception {
        willThrow(new InvalidInputException())
                .given(helloWorldService)
                .createMessage("zara");

        mockMvc.perform(get("/hello-world")
                        .param("name", "zara"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error")
                        .value("Invalid Input"));
    }

    @Test
    void shouldReturnBadRequestWhenNameIsMissing() throws Exception {
        willThrow(new InvalidInputException())
                .given(helloWorldService)
                .createMessage(null);

        mockMvc.perform(get("/hello-world"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error")
                        .value("Invalid Input"));
    }

    @Test
    void shouldReturnBadRequestWhenNameIsEmpty() throws Exception {
        willThrow(new InvalidInputException())
                .given(helloWorldService)
                .createMessage("");

        mockMvc.perform(get("/hello-world")
                        .param("name", ""))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error")
                        .value("Invalid Input"));
    }
}
