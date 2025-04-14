package com.example.springemotiondiaryapi.domain.diary;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryId;

    private int emotionId;

    private String content;

    private LocalDateTime createdDate;

    @Builder
    public Diary(Long diaryId, int emotionId, String content, LocalDateTime createdDate) {
        this.diaryId = diaryId;
        this.emotionId = emotionId;
        this.content = content;
        this.createdDate = createdDate;
    }
}
