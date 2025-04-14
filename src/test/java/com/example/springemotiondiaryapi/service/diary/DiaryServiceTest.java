package com.example.springemotiondiaryapi.service.diary;

import com.example.springemotiondiaryapi.IntegrationTestSupport;
import com.example.springemotiondiaryapi.domain.diary.DiaryRepositoryJpa;
import com.example.springemotiondiaryapi.dto.diary.DiaryCreateRequest;
import com.example.springemotiondiaryapi.dto.diary.DiaryCreateResponse;
import com.example.springemotiondiaryapi.service.DiaryService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DiaryServiceTest extends IntegrationTestSupport {

    @Autowired
    private DiaryService diaryService;

    @Autowired
    private DiaryRepositoryJpa diaryRepositoryJpa;

    @AfterEach
    void tearDown() {
        diaryRepositoryJpa.deleteAll();
    }

    @DisplayName("일기를 등록 한다.")
    @Test
    void createDiary() throws Exception {
        //given
        DiaryCreateRequest request = DiaryCreateRequest
                .builder()
                .emotionId(1)
                .content("일기 내용 테스트")
                .build();

        //when
        DiaryCreateResponse response = diaryService.createDiary(request);

        //then
        assertThat(response).isNotNull();
        assertThat(response.getEmotionId()).isEqualTo(1);
        assertThat(response.getContent()).isEqualTo("일기 내용 테스트");


    }
}