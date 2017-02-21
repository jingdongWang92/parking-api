package com.jcble.parking.api.interceptor;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.collect.Maps;
import com.jcble.parking.common.dto.BaseRespDto;

@Component
@Aspect
public class LogPrintControllerAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogPrintControllerAspect.class);

    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void cutController(){
    }

    @Around("cutController()")
    public Object recordSysLog(ProceedingJoinPoint point) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Enumeration<String> parameterNames = request.getParameterNames();
        Map<String, String> paramNameMap = Maps.newHashMap();
        String parameterName = null;
        while (parameterNames.hasMoreElements()) {
            parameterName = parameterNames.nextElement();
            if (StringUtils.isBlank(parameterName)) {
                continue;
            }
            paramNameMap.put(parameterName, request.getParameter(parameterName));
        }
        logger.info(request.getRequestURI() + " request==>" + "[" + paramNameMap.toString() + "]");

        long beginTime = System.currentTimeMillis();// 1、开始时间

        Object result = point.proceed();

        long endTime = System.currentTimeMillis();// 2、结束时间
        long consumeTime = endTime - beginTime;// 3、消耗的时间

        if(result != null && result instanceof BaseRespDto) {
            logger.info(String.format("parking-api==>%s consume %d millis, resp ==> %s", request.getRequestURI(), consumeTime, result.toString()));
        }
        return result;
    }
}
