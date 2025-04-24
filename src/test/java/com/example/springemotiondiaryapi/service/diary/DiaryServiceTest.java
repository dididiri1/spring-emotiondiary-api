package com.example.springemotiondiaryapi.service.diary;

import com.example.springemotiondiaryapi.IntegrationTestSupport;
import com.example.springemotiondiaryapi.domain.diary.Diary;
import com.example.springemotiondiaryapi.domain.diary.DiaryRepositoryJpa;
import com.example.springemotiondiaryapi.dto.diary.request.DiaryCreateRequest;
import com.example.springemotiondiaryapi.dto.diary.request.DiaryUpdateRequest;
import com.example.springemotiondiaryapi.dto.diary.response.DiaryCreateResponse;
import com.example.springemotiondiaryapi.dto.diary.response.DiaryResponse;
import com.example.springemotiondiaryapi.dto.diary.response.DiaryUpdateResponse;
import com.example.springemotiondiaryapi.service.DiaryService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


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

    @DisplayName("일기를 전체 목록을 한다.")
    @Test
    void getDiaryList() throws Exception {
        //given
        Diary diary1 = createDiary(1, "일기 내용1");
        Diary diary2 = createDiary(3, "일기 내용2");

        diaryRepositoryJpa.saveAll(List.of(diary1, diary2));

        //when
        List<DiaryResponse> response = diaryService.getDiaryList();

        //then
        assertThat(response).hasSize(2)
                .extracting("emotionId", "content")
                .containsExactlyInAnyOrder(
                        tuple(1, "일기 내용1"),
                        tuple(3, "일기 내용2")
                );
    }

    @DisplayName("해당 일기를 조회 한다.")
    @Test
    void getDiary() throws Exception {
        //given
        Diary diary1 = createDiary(1, "일기 내용1");
        Diary diary2 = createDiary(3, "일기 내용2");

        diaryRepositoryJpa.saveAll(List.of(diary1, diary2));

        //when
        DiaryResponse response = diaryService.getDiary(diary1.getId());

        //then
        assertThat(response).isNotNull();
        assertThat(response.getEmotionId()).isEqualTo(1);
        assertThat(response.getContent()).isEqualTo("일기 내용1");
    }

    @DisplayName("해당 일기를 수정 한다.")
    @Test
    void updateDiaryTest() throws Exception {
        //given
        Diary diary = createDiary(1, "일기 내용1");
        diaryRepositoryJpa.save(diary);

        DiaryUpdateRequest request = DiaryUpdateRequest.builder()
                .emotionId(3)
                .content("일기 내용 수정 테스트")
                .build();

        //when
        DiaryUpdateResponse response = diaryService.updateDiary(diary.getId(), request);

        //then
        assertThat(response).isNotNull();
        assertThat(response.getEmotionId()).isEqualTo(3);
        assertThat(response.getContent()).isEqualTo("일기 내용 수정 테스트");

    }

    @DisplayName("해당 일기를 삭제 한다.")
    @Test
    void deleteDiaryTest() throws Exception {
        //given
        Diary diary = createDiary(1, "일기 내용1");
        diaryRepositoryJpa.save(diary);

        //when
        diaryService.deleteDiary(diary.getId());

        //then
        Optional<Diary> findDiary = diaryRepositoryJpa.findById(diary.getId());
        assertThat(findDiary).isEmpty();

    }

    private Diary createDiary(int emotionId, String content) {
        return Diary.builder()
                .emotionId(emotionId)
                .content(content)
                .build();
    }
}