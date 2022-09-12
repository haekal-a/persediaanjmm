package com.tamu.controller.exception;

import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.table.User;
import com.tamu.util.AppException;
import com.tamu.util.Constanta;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
@Aspect
public class ValidationHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(CheckEx.class);

    @Around("execution(com.tamu.domain.model.ResponseModel com.tamu.controller..save*(..)) ")
    public ResponseModel submitValidation(ProceedingJoinPoint joinPoint) throws Throwable {
        validateUser();
        return (ResponseModel) joinPoint.proceed();
    }

    @Around("execution(com.tamu.domain.model.ResponseModel com.tamu.controller..delete*(..)) ")
    public ResponseModel deleteValidation(ProceedingJoinPoint joinPoint) throws Throwable {
        validateUser();
        return (ResponseModel) joinPoint.proceed();
    }

    private void validateUser(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userLogin");

        if (user.getLevel().equals(Constanta.LEVEL_USER)){
            throw new AppException(1, "Action not allowed");
        }
    }
}
