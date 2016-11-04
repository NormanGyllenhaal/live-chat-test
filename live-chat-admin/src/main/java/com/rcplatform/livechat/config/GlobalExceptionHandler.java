package com.rcplatform.livechat.config;

import com.rcplatform.livechat.common.enums.StatEnum;
import com.rcplatform.livechat.common.response.Response;
import org.omg.CORBA.Object;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by yang peng on 2016/9/30.
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response<Object> defaultErrorHandler(HttpServletRequest req, Exception e) {
        Response<Object> response = new Response<>(StatEnum.STAT_ERROR.key(), StatEnum.STAT_ERROR.desc());
        return response;
    }
}
