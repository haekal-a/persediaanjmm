package com.tamu.controller;

import com.tamu.domain.table.User;
import com.tamu.service.common.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {

    @Autowired
    private SecurityService securityService;

    @GetMapping({"/", "/dashboard"})
    public String showHome(HttpServletRequest request, ModelMap model) {
        User user = (User) request.getSession().getAttribute("userLogin");
        model.addAttribute("user", user);
        model.addAttribute("dashboardMenu", "active");
        return "pencarian-npwp";
    }

    @GetMapping(value = "/login")
    public String login(ModelMap model, String error, String logout) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null) {
            String errMsg = error.equals("invalidsession") ? "Your session is invalid." : "Your username and password is invalid.";
            model.addAttribute("error", errMsg);
        }

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping("/pengaturanrole")
    public String showPengaturanRole(HttpServletRequest request, ModelMap model){
        User user = (User) request.getSession().getAttribute("userLogin");
        model.addAttribute("user", user);
        model.addAttribute("roleMenu", "active");
        return "role";
    }

    @GetMapping("/monitoringkunjungan")
    public String showDaftarKunjungan(HttpServletRequest request, ModelMap model){
        User user = (User) request.getSession().getAttribute("userLogin");
        model.addAttribute("user", user);
        model.addAttribute("kunjunganMenu", "active");
        return "daftar_kunjungan";
    }
}
