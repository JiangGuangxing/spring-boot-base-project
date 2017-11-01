package com.github.JiangGuangxing.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 姜广兴
 * @since 2017/11/1
 * 统一异常处理
 */

@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {
    private Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        LOGGER.error("异常信息", e);
        return Result.failed();
    }
}
