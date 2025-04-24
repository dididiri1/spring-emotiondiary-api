package com.example.springemotiondiaryapi.dto.diary.response;

import com.example.springemotiondiaryapi.domain.diary.Diary;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DiaryResponse {

    private Long id;

    private int emotionId;

    private String content;

    private Long createdDate;

    @Builder
    public DiaryResponse(Long id, int emotionId, String content, Long createdDate) {
        this.id = id;
        this.emotionId = emotionId;
        this.content = content;
        this.createdDate = createdDate;
    }

    public static DiaryResponse of(Diary diary) {
        return DiaryResponse.builder()
                .id(diary.getId())
                .emotionId(diary.getEmotionId())
                .content(diary.getContent())
                .createdDate(diary.getCreatedDate())
                .build();
    }
}
