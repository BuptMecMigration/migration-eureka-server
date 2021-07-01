package com.controller;


import com.error.BusinessException;
import com.error.SystemError;
import com.response.CommonReturnType;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonReturnType doError(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Exception ex) {

        ex.printStackTrace();
        Map<String,Object> responseData = new HashMap<>();
        if( ex instanceof BusinessException){

            BusinessException businessException = (BusinessException)ex;
            responseData.put("errCode", businessException.getErrCode());
            responseData.put("errMsg", businessException.getErrMsg());

        } else if ( ex instanceof ServletRequestBindingException){

            responseData.put("errCode", SystemError.UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg","url绑定路由问题");

        } else if ( ex instanceof UnsupportedEncodingException
                || ex instanceof NoSuchAlgorithmException) {

            responseData.put("errCode", SystemError.UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg","MD5加密算法错误");

        } else if ( ex instanceof NoHandlerFoundException){

            responseData.put("errCode", SystemError.UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg","对应的访问路径无法找到");

        } else {

            responseData.put("errCode", SystemError.UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg", SystemError.UNKNOWN_ERROR.getErrMsg());

        }
        return CommonReturnType.create(responseData,"fail");
    }
}
