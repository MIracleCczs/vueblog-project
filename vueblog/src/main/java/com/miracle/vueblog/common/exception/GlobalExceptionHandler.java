package com.miracle.vueblog.common.exception;

import com.miracle.vueblog.common.Result;
import org.apache.shiro.ShiroException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

/**
 * @program: vueblog-project
 * @description: 全局异常处理器
 * @author: miracle
 * @create: 2020-08-09 09:43
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * shiro 权限异常全局捕获
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ShiroException.class)
    public Result handler(ShiroException e) {
        LOGGER.error("===========访问权限异常==========", e);
        return Result.fail(401, e.getMessage(), null);
    }

    /**
     * 参数校验异常全局捕获
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e) {
        LOGGER.error("===========参数校验异常==========", e);
        BindingResult bindingResult = e.getBindingResult();
        Optional<ObjectError> error = bindingResult.getAllErrors().stream().findFirst();
        return error.map(objectError -> Result.fail(objectError.getDefaultMessage())).orElseGet(() -> Result.fail(e.getMessage()));
    }

    /**
     * runtime exception 全局异常捕获
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(RuntimeException e) {
        LOGGER.error("===========运行时异常==========", e);
        return Result.fail(e.getMessage());
    }

}
