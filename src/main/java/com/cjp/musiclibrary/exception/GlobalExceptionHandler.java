package com.cjp.musiclibrary.exception;

import com.cjp.musiclibrary.comment.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author CJP
 * @version 1.0
 */
@RestControllerAdvice("com.cjp.musiclibrary.controller")
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public <E> Result<E> error(Exception e) {
        e.printStackTrace();
        return Result.error("500", "系统错误");
    }

    @ExceptionHandler(CustomException.class)
    public <E> Result<E> error(CustomException e) {
        e.printStackTrace();
        return Result.error(e.getCode(), e.getMsg());
    }
}
