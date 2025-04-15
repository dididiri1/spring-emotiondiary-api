package com.example.springemotiondiaryapi.dto.diary.request;

import com.example.springemotiondiaryapi.domain.diary.Diary;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DiaryCreateRequest {

    @NotNull(message = "emotionId 는 값입니다.")
    @Positive(message = "emotionId 는 0보다 커야 합니다.")
    private int emotionId;

    @NotBlank(message = "content 는 필수 값입니다.")
    private String content;

    @Builder
    private DiaryCreateRequest(int emotionId, String content) {
        this.emotionId = emotionId;
        this.content = content;
    }

    public Diary toEntity(DiaryCreateRequest request) {
        return Diary.builder()
                .emotionId(request.emotionId)
                .content(request.content)
                .build();
    }
}
