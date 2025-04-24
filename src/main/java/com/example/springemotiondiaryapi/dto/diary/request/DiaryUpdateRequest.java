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
public class DiaryUpdateRequest {

    @NotNull(message = "emotionId 는 값입니다.")
    @Positive(message = "emotionId 는 0보다 커야 합니다.")
    private int emotionId;

    @NotBlank(message = "content 는 필수 값입니다.")
    private String content;

    @NotNull(message = "createdDate 는 값입니다.")
    @Positive(message = "createdDate 는 0보다 커야 합니다.")
    private Long createdDate;

    @Builder
    private DiaryUpdateRequest(int emotionId, String content, Long createdDate) {
        this.emotionId = emotionId;
        this.content = content;
        this.createdDate = createdDate;
    }

    public Diary toEntity(DiaryUpdateRequest request) {
        return Diary.builder()
                .emotionId(request.emotionId)
                .content(request.content)
                .createdDate(request.createdDate)
                .build();
    }

    @Override
    public String toString() {
        return "DiaryUpdateRequest{" +
                "emotionId=" + emotionId +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
