package com.example.springemotiondiaryapi.service;


import com.example.springemotiondiaryapi.domain.diary.Diary;
import com.example.springemotiondiaryapi.domain.diary.DiaryRepositoryJpa;
import com.example.springemotiondiaryapi.dto.diary.request.DiaryCreateRequest;
import com.example.springemotiondiaryapi.dto.diary.request.DiaryUpdateRequest;
import com.example.springemotiondiaryapi.dto.diary.response.DiaryCreateResponse;
import com.example.springemotiondiaryapi.dto.diary.response.DiaryResponse;
import com.example.springemotiondiaryapi.dto.diary.response.DiaryUpdateResponse;
import com.example.springemotiondiaryapi.handler.ex.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<DiaryResponse> getDiaryList() {
        List<Diary> diaryList = diaryRepositoryJpa.findAll();

        return diaryList.stream().map(
                diary -> DiaryResponse.of(diary))
                .collect(Collectors.toList());
    }

    public DiaryResponse getDiary(Long diaryId) {
        Diary diary = findDiaryById(diaryId);

        return DiaryResponse.of(diary);
    }

    @Transactional
    public DiaryUpdateResponse updateDiary(Long diaryId, DiaryUpdateRequest request) {
        Diary diary = findDiaryById(diaryId);

        diary.setEmotionId(request.getEmotionId());
        diary.setContent(request.getContent());

        return DiaryUpdateResponse.of(diary);
    }

    private Diary findDiaryById(Long diaryId) {
        Diary findDiary = diaryRepositoryJpa.findById(diaryId).orElseThrow(() -> {
            throw new CustomException("해당 일기가 없습니다.");
        });

        return findDiary;
    }
}
