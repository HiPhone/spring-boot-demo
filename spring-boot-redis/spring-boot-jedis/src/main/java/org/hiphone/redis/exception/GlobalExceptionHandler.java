package org.hiphone.redis.exception;

import lombok.extern.slf4j.Slf4j;
import org.hiphone.redis.entitys.ResultMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author HiPhone
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 未知错误的异常处理
     * @param ex 抛出的exception
     * @return 返回前端的封装数据
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultMessage handleException(Exception ex) {
        log.error("发生未知错误： ", ex);
        return new ResultMessage(
                ReturnMsg.UNKNOWN_ERROR.getCode(),
                ReturnMsg.UNKNOWN_ERROR.getMessage(),
                ex.getMessage()
        );
    }
}
