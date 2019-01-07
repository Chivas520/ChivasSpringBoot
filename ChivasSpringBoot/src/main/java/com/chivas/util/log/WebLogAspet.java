package com.chivas.util.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Aspect //声明注解
@Component
public class WebLogAspet {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspet.class);


    /**
     * @description:定义切入点
     * @author: RunFa.Zhou
     * @create: 2019/1/7
     **/
    @Pointcut("execution(public * com.chivas.controller.*.*(..))")
    public void webLog(){

    }



    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        logger.info("URL:"+request.getRequestURI().toString());
        logger.info("Method:"+request.getMethod().toString());
        logger.info("IP:"+request.getRemoteAddr().toString());

        Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()){
            String name = parameterNames.nextElement();
            logger.info("name:{},value={}",name,request.getParameter(name));
        }
    }


}

