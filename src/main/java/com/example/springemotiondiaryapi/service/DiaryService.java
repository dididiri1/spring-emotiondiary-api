package com.example.springemotiondiaryapi.service;


import com.example.springemotiondiaryapi.domain.diary.Diary;
import com.example.springemotiondiaryapi.domain.diary.DiaryRepositoryJpa;
import com.example.springemotiondiaryapi.dto.diary.DiaryCreateRequest;
import com.example.springemotiondiaryapi.dto.diary.DiaryCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class DiaryService {

    private final DiaryRepositoryJpa diaryRepositoryJpa;

    public DiaryCreateResponse createDiary(DiaryCreateRequest request) {
        Diary diary = request.toEntity(request);
        Diary diaryEntity = diaryRepositoryJpa.save(diary);

        return DiaryCreateResponse.of(diaryEntity);
    }
}
