package com.application.controller;

import com.application.service.ParserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringJUnitWebConfig
@WebMvcTest(ParserController.class)
class ParserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private ParserService service;

    @Test
    void shouldParseVarDumpString() throws Exception {

        String inputString = Files.readString(
                Path.of("src/test/resources/test_input_1"), StandardCharsets.UTF_8);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/php_parser")
                        .contentType(MediaType.ALL_VALUE)
                        .characterEncoding("UTF-8")
                        .content(inputString))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}