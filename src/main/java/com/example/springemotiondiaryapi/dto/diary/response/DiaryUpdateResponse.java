package com.example.springemotiondiaryapi.dto.diary.response;

import com.example.springemotiondiaryapi.domain.diary.Diary;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DiaryUpdateResponse {

    private Long id;

    private int emotionId;

    private String content;

    private Long createdDate;

    @Builder
    public DiaryUpdateResponse(Long id, int emotionId, String content, Long createdDate) {
        this.id = id;
        this.emotionId = emotionId;
        this.content = content;
        this.createdDate = createdDate;
    }

    public static DiaryUpdateResponse of(Diary diary) {
        return DiaryUpdateResponse.builder()
                .id(diary.getId())
                .emotionId(diary.getEmotionId())
                .content(diary.getContent())
                .createdDate(diary.getCreatedDate())
                .build();
    }
}
