package com.tamu.controller.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class AppErrorException implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Exception ex = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.forLanguageTag("ID"));
                SimpleDateFormat sdf2 = new SimpleDateFormat("MMddyyHHmmss");
                String time = sdf.format(new Date());
                String errId = sdf2.format(new Date());
                String errMsg = ex.getMessage() == null || ex.getMessage().equals("") ? "Internal Server Error" : ex.getMessage();
                if(errMsg.contains("URL contained a potentially malicious String")){
                    return "redirect:/";
                }
                model.addAttribute("statusCode", "500");
                model.addAttribute("errorId", errId);
                model.addAttribute("errorMessage", errMsg);
                return "error";
            } else {
                model.addAttribute("statusCode", "404");
                return "error";
            }
        }
        model.addAttribute("statusCode", "500");
        return "error";
    }
}

