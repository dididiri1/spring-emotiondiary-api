package com.example.springemotiondiaryapi.dto.diary.response;

import com.example.springemotiondiaryapi.domain.diary.Diary;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DiaryResponse {

    private Long diaryId;

    private int emotionId;

    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDateTime;

    @Builder
    public DiaryResponse(Long diaryId, int emotionId, String content, LocalDateTime createdDateTime) {
        this.diaryId = diaryId;
        this.emotionId = emotionId;
        this.content = content;
        this.createdDateTime = createdDateTime;
    }

    public static DiaryResponse of(Diary diary) {
        return DiaryResponse.builder()
                .diaryId(diary.getDiaryId())
                .emotionId(diary.getEmotionId())
                .content(diary.getContent())
                .createdDateTime(diary.getCreatedDatetime())
                .build();
    }
}
