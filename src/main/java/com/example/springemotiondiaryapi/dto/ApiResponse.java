package com.example.springemotiondiaryapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
public class ApiResponse<T>{

    private int status;
    private String message;
    private T data;


    public ApiResponse(int status) {
        super();
        this.status = status;
    }

}