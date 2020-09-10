package com.bupt.trainbookingsystem.exception;

import com.bupt.trainbookingsystem.bean.ResponseData;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局统一异常处理
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseData exceptionHandler(Exception e){
        e.printStackTrace();
        return ResponseData.fail("服务器异常：" + e.getMessage());
    }

}
