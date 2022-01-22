package com.aaa.blog.exception;

import com.aaa.blog.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ydxstar
 * @create 2022-01-06 15:22
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(RuntimeException r){
        log.error("运行时异常============={}",r);
        return Result.fail(r.getMessage());
    }
    @ExceptionHandler(value = ShiroException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result handler(ShiroException s){
        log.error("shiro异常=============={}",s);
        return Result.fail(401,s.getMessage(),null);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e){
        log.error("实体校验异常=========={}",e);
        BindingResult bindingResult = e.getBindingResult();
        ObjectError error = bindingResult.getAllErrors().stream().findFirst().get();
        return  Result.fail(402,error.getDefaultMessage(),null);
    }

}
