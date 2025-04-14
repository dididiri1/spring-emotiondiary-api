package com.example.springemotiondiaryapi.controller.api;

import com.example.springemotiondiaryapi.dto.ApiResponse;
import com.example.springemotiondiaryapi.dto.diary.DiaryCreateRequest;
import com.example.springemotiondiaryapi.dto.diary.DiaryCreateResponse;
import com.example.springemotiondiaryapi.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DiaryApiController {

    private DiaryService diaryService;

    @PostMapping("/api/v1/diary")
    public ResponseEntity<?> createDiary(@RequestBody DiaryCreateRequest request, BindingResult bindingResult) {
        DiaryCreateResponse response = diaryService.createDiary(request);

        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "일기 등록 성공", response), HttpStatus.CREATED);
    }


}
