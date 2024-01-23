package com.example.backend.util;


import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public  ApiResponse handleExecption(Exception e)
    {
        e.printStackTrace();
        return new ApiResponse(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "操作失败", "操作失败",null );
    }




}
