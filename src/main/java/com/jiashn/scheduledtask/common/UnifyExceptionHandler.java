package com.jiashn.scheduledtask.common;

import com.jiashn.scheduledtask.utils.ResultJson;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: jiangjs
 * @description: 统一异常处理
 * @date: 2021/9/6 16:48
 */
@RestControllerAdvice
public class UnifyExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultJson<?> handlerMethodArgument(MethodArgumentNotValidException ex){
        String defaultMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResultJson.error(400,defaultMessage);
    }
}
