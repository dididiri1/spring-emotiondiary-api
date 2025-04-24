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
    private Long id;

    private int emotionId;

    private String content;

    private Long createdDate;

    @Builder
    public Diary(Long id, int emotionId, String content, Long createdDate) {
        this.id = id;
        this.emotionId = emotionId;
        this.content = content;
        this.createdDate = createdDate;
    }
}
