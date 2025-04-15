package com.example.springemotiondiaryapi;

import com.example.springemotiondiaryapi.config.aop.BindingAspect;
import com.example.springemotiondiaryapi.controller.api.DiaryApiController;
import com.example.springemotiondiaryapi.handler.GlobalExceptionHandler;
import com.example.springemotiondiaryapi.handler.ex.CustomApiException;
import com.example.springemotiondiaryapi.service.DiaryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockitoBean
    protected DiaryService diaryService;



}
