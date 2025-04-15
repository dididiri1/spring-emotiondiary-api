package com.example.springemotiondiaryapi.controller.api;

import com.example.springemotiondiaryapi.dto.ApiResponse;
import com.example.springemotiondiaryapi.dto.diary.request.DiaryCreateRequest;
import com.example.springemotiondiaryapi.dto.diary.request.DiaryUpdateRequest;
import com.example.springemotiondiaryapi.dto.diary.response.DiaryCreateResponse;
import com.example.springemotiondiaryapi.dto.diary.response.DiaryResponse;
import com.example.springemotiondiaryapi.dto.diary.response.DiaryUpdateResponse;
import com.example.springemotiondiaryapi.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class DiaryApiController {

    private final DiaryService diaryService;

    @PostMapping("/api/v1/diary")
    public ResponseEntity<?> createDiary(@RequestBody DiaryCreateRequest request, BindingResult bindingResult) {
        DiaryCreateResponse response = diaryService.createDiary(request);

        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "일기 등록 성공", response), HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/diary")
    public ResponseEntity<?> getDiaryList() {
        List<DiaryResponse> response = diaryService.getDiaryList();

        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "일기 목록 조회 성공", response), HttpStatus.OK);
    }

    @GetMapping("/api/v1/diary/{diaryId}")
    public ResponseEntity<?> getDiary(@PathVariable("diaryId") Long diaryId) {
        DiaryResponse response = diaryService.getDiary(diaryId);

        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "일기 단건 조회 성공", response), HttpStatus.OK);
    }

    @PatchMapping("/api/v1/diary/{diaryId}")
    public ResponseEntity<?> updateDiary(@PathVariable("diaryId") Long diaryId, @RequestBody DiaryUpdateRequest request) {
        DiaryUpdateResponse response = diaryService.updateDiary(diaryId, request);

        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "일기 수정 성공", response), HttpStatus.OK);
    }

}
