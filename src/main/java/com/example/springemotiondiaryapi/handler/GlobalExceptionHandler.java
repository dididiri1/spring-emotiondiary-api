package com.example.springemotiondiaryapi.handler;

import com.example.springemotiondiaryapi.dto.ApiResponse;
import com.example.springemotiondiaryapi.handler.ex.BadRequestException;
import com.example.springemotiondiaryapi.handler.ex.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<?>> handleBadRequest(BadRequestException e) {
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<?>> customException(CustomException e) {
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), e.getMessage(), null), HttpStatus.NOT_FOUND);
    }
}
