package com.example.springemotiondiaryapi.domain.diary;

import com.example.springemotiondiaryapi.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Entity
public class Diary extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryId;

    private int emotionId;

    private String content;

    @Builder
    public Diary(Long diaryId, int emotionId, String content) {
        this.diaryId = diaryId;
        this.emotionId = emotionId;
        this.content = content;
    }
}
