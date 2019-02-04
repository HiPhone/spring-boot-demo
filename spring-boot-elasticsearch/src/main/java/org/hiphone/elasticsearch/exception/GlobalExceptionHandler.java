package org.hiphone.elasticsearch.exception;

import lombok.extern.slf4j.Slf4j;
import org.hiphone.elasticsearch.entitys.ResultMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author HiPhone
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 未知异常的处理
     * @param ex 异常
     * @return 异常提示
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

    /**
     * 请求404的处理
     * @param ex 异常
     * @return 异常提示
     */
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultMessage handleException(HttpClientErrorException.NotFound ex) {
        log.error("请求页面未找到： ", ex);
        return new ResultMessage(
                ReturnMsg.INDEX_NOT_FOUND.getCode(),
                ReturnMsg.INDEX_NOT_FOUND.getMessage(),
                ex.getMessage()
        );
    }

    /**
     * 请求400的处理
     * @param ex 异常
     * @return 异常提示
     */
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultMessage handleException(HttpClientErrorException.BadRequest ex) {
        log.error("请求被拒绝： ", ex);
        return new ResultMessage(
                ReturnMsg.INDEX_EXISTS.getCode(),
                ReturnMsg.INDEX_EXISTS.getMessage(),
                ex.getMessage()
        );
    }

    /**
     * 请求405的处理
     * @param ex 异常
     * @return 异常提示
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResultMessage handleException(HttpRequestMethodNotSupportedException ex) {
        log.error("http请求方法错误： ", ex);
        return new ResultMessage(
                ReturnMsg.METHOD_NOT_SUPPORT.getCode(),
                ReturnMsg.METHOD_NOT_SUPPORT.getMessage(),
                ex.getMessage()
        );
    }
}
