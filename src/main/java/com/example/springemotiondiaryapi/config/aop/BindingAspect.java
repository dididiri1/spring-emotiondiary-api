package com.example.springemotiondiaryapi.config.aop;

import com.example.springemotiondiaryapi.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;


import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@Aspect
public class BindingAspect {

    @Around("execution(* com.example.springemotiondiaryapi.controller.api..*(..))")
    public Object validCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("🔍 AOP BindingAspect 동작 시작");

        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg:args){
            if(arg instanceof BindingResult){
                BindingResult bindingResult = (BindingResult) arg;
                if(bindingResult.hasErrors()){
                    log.warn("❌ 검증 오류 발생: {}", bindingResult.getFieldErrors());

                    Map<String,String> errorMap = new HashMap<>();
                    for(FieldError error:bindingResult.getFieldErrors()){
                        errorMap.put(error.getField(),error.getDefaultMessage());
                    }
                    return new ResponseEntity<>(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "잘못된 요청입니다.", errorMap), HttpStatus.BAD_REQUEST);
                }
            }
        }

        return proceedingJoinPoint.proceed();
    }
}