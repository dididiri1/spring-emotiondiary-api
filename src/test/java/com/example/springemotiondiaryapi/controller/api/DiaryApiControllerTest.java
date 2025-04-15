package com.example.springemotiondiaryapi.controller.api;

import com.example.springemotiondiaryapi.ControllerTestSupport;
import com.example.springemotiondiaryapi.dto.diary.request.DiaryCreateRequest;
import com.example.springemotiondiaryapi.dto.diary.request.DiaryUpdateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class DiaryApiControllerTest extends ControllerTestSupport {

    @DisplayName("새 일기를 등록한다.")
    @Test
    void createDiary() throws Exception {
        //given
        DiaryCreateRequest request = DiaryCreateRequest.builder()
                .emotionId(1)
                .content("일기 내용 입니다.")
                .build();

        //when //then
        mockMvc.perform(post("/api/v1/diary")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @DisplayName("새 일기를 등록시 이모션 아이디는 필수값이다.")
    @Test
    void createDiaryWithEmotionId() throws Exception {
        //given
        DiaryCreateRequest request = DiaryCreateRequest.builder()
                .content("일기 내용 입니다.")
                .build();

        //when //then
        mockMvc.perform(post("/api/v1/diary")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."));

    }

    @DisplayName("새 일기를 등록시 컨텐츠는 필수값이다.")
    @Test
    void createDiaryWithContent() throws Exception {
        //given
        DiaryCreateRequest request = DiaryCreateRequest.builder()
                .build();

        //when //then
        mockMvc.perform(post("/api/v1/diary")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."));

    }

    @DisplayName("일기 목록 전체를 조회 한다.")
    @Test
    void getDiaryList() throws Exception {
        //given //when //then
        mockMvc.perform(get("/api/v1/diary")
                        .contentType(APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @DisplayName("해당 일기 정보를 조회 한다.")
    @Test
    void getDiary() throws Exception {
        //given
        long diaryId = 1L;

        //when //then
        mockMvc.perform(get("/api/v1/diary/{id}", diaryId)
                        .contentType(APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @DisplayName("기존 일기를 수정한다.")
    @Test
    void updateDiary() throws Exception {
        //given
        long diaryId = 1L;

        DiaryUpdateRequest request = DiaryUpdateRequest.builder()
                .emotionId(1)
                .content("수정 일기 내용 입니다.")
                .build();

        //when //then
        mockMvc.perform(patch("/api/v1/diary/{id}", diaryId)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("해당 일기 정보를 삭제 한다.")
    @Test
    void deleteDiary() throws Exception {
        //given
        long diaryId = 1L;

        //when //then
        mockMvc.perform(delete("/api/v1/diary/{id}", diaryId)
                        .contentType(APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").exists())
                .andExpect(jsonPath("$.message").exists());
    }
}
