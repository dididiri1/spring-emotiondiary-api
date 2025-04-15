package com.example.springemotiondiaryapi.dto.diary.response;

import com.example.springemotiondiaryapi.domain.diary.Diary;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DiaryUpdateResponse {

    private Long diaryId;

    private int emotionId;

    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;

    @Builder
    public DiaryUpdateResponse(Long diaryId, int emotionId, String content, LocalDateTime createdDate) {
        this.diaryId = diaryId;
        this.emotionId = emotionId;
        this.content = content;
        this.createdDate = createdDate;
    }

    public static DiaryUpdateResponse of(Diary diary) {
        return DiaryUpdateResponse.builder()
                .diaryId(diary.getDiaryId())
                .emotionId(diary.getEmotionId())
                .content(diary.getContent())
                .createdDate(diary.getCreatedDate())
                .build();
    }
}
