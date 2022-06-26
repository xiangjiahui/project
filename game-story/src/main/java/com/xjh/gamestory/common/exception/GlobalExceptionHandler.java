package com.xjh.gamestory.common.exception;


import com.xjh.gamestory.common.JsonResult;
import com.xjh.gamestory.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 * @author 向嘉晖
 * @date 2022/6/26
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = UserNotFoundException.class)
    public JsonResult handleUserException(UserNotFoundException e){
        String message = e.getMessage();
        return message != null ? JsonResult.error(message) : JsonResult.success();
    }
}
