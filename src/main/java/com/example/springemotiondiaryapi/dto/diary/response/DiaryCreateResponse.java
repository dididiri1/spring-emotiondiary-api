package com.example.springemotiondiaryapi.dto.diary.response;

import com.example.springemotiondiaryapi.domain.diary.Diary;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DiaryCreateResponse {

    private Long id;

    private int emotionId;

    private String content;

    private Long createdDate;

    /*@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDateTime;*/

    @Builder
    public DiaryCreateResponse(Long id, int emotionId, String content, Long createdDate) {
        this.id = id;
        this.emotionId = emotionId;
        this.content = content;
        this.createdDate = createdDate;
    }

    public static DiaryCreateResponse of(Diary diary) {
        return DiaryCreateResponse.builder()
                .id(diary.getId())
                .emotionId(diary.getEmotionId())
                .content(diary.getContent())
                .createdDate(diary.getCreatedDate())
                .build();
    }
}
