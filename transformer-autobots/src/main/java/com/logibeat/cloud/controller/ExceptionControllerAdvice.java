package com.logibeat.cloud.controller;

import com.logibeat.cloud.util.tools.exception.ServiceException;
import com.logibeat.cloud.util.tools.exception.TException;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author maidesen
 */
@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {


    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public JSONPrompt errorHandler(Exception ex) {
        log.info("",ex);
        JSONPrompt res = new JSONPrompt<>();
        res.setSuc(false);
        res.setErrCode("-1");
        res.setMessage("API请求异常");

        return res;
    } 


    /**
     * 全局异常捕捉处理
     *
     * @param
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public JSONPrompt errorHandler(ServiceException biz) {
        log.info("",biz);
        JSONPrompt res = new JSONPrompt<>();
        res.setSuc(false);
        res.setErrCode(biz.getCode());
        res.setMessage(biz.getMessage());
        return res;
    }

    @ResponseBody
    @ExceptionHandler(value = TException.class)
    public JSONPrompt errorHandler(TException biz) {
        log.info("",biz);
        JSONPrompt res = new JSONPrompt<>();
        res.setSuc(false);
        res.setErrCode(biz.getCode());
        res.setMessage(biz.getMessage());
        return res;
    }

    /**
     * 全局异常捕捉处理
     *
     * @param
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public JSONPrompt errorHandler(HttpMessageNotReadableException biz) {
        log.info("",biz);
        JSONPrompt res = new JSONPrompt<>();
        res.setSuc(false);
        res.setErrCode("-1");
        res.setMessage("请求异常");
        return res;
    }

    /**
     * 全局异常捕捉处理
     *
     * @param
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public JSONPrompt errorHandler(NoHandlerFoundException biz) {
        log.info("",biz);
        JSONPrompt res = new JSONPrompt<>();
        res.setSuc(false);
        res.setErrCode("404");
        res.setMessage("未找到地址");
        return res;
    }

}
