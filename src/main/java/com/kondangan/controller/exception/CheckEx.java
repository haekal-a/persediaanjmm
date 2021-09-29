package com.kondangan.controller.exception;

import com.kondangan.domain.model.ResponseModel;
import com.kondangan.domain.model.UserFormModel;
import com.kondangan.domain.table.User;
import com.kondangan.util.AppException;
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
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
@Aspect
public class CheckEx {
    public static final Logger LOGGER = LoggerFactory.getLogger(CheckEx.class);

    @Around("execution(com.kondangan.domain.model.ResponseModel com.kondangan.controller..*(..)) ")
    public ResponseModel checkController(ProceedingJoinPoint joinPoint) {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpSession session = request.getSession();
//        UserFormModel user = (UserFormModel) session.getAttribute("userLogin");
        User user = (User) request.getSession().getAttribute("userLogin");

        Object[] args = joinPoint.getArgs();

        SimpleDateFormat sdf = new SimpleDateFormat("MMddyyHHmmss");
        String username = user != null ? user.getUsername() : "Failed to get user";
        ResponseModel result = null;
        try {
            result = (ResponseModel) joinPoint.proceed(args);
        } catch (Throwable  ex) {
            String errId = sdf.format(new Date());

            LOGGER.error("USER [" + username + " - " + errId + "] ERROR : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                    + joinPoint.getSignature().getName() + " - " + ex.getMessage() );
//			ex.printStackTrace();
            result = new ResponseModel();
            result.setCode(0);
            result.setTitle("Terjadi Kesalahan");

            if (!(ex instanceof AppException)) {
                ex.printStackTrace();
            }else{
                if(((AppException) ex).getLevel() == AppException.LEVEL_ERROR){
                    ex.printStackTrace();
                }
            }

            if (ex.getMessage() != null) {
                result.setMessage(ex.getMessage() + ". \n[ID : " + errId + "] " );
            } else {
                result.setMessage("Terjadi kesalahan pada server. \n[ID : " + errId + "] ");
            }
        }

        return result;
    }

}
