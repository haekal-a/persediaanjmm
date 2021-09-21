package com.kondangan.controller;

import com.kondangan.service.common.SecurityService;
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
        model.addAttribute("dashboardMenu", "active");
        return "dashboard";
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

    @GetMapping("/si/dashboard")
    public String showSiDashboard(HttpServletRequest request, ModelMap model){
//        UserFormModel user = (UserFormModel) request.getSession().getAttribute("userLogin");
//        model.addAttribute("user", user);
        model.addAttribute("siDeliverableHeader", "menu-open");
        model.addAttribute("siDeliverableMenu", "active");
        model.addAttribute("siDashboardMenu", "active");
        return "dashboard";
    }

    @GetMapping("/si/master")
    public String showSiMaster(HttpServletRequest request, ModelMap model){
//        UserFormModel user = (UserFormModel) request.getSession().getAttribute("userLogin");
//        model.addAttribute("user", user);
        model.addAttribute("siDeliverableHeader", "menu-open");
        model.addAttribute("siDeliverableMenu", "active");
        model.addAttribute("siMasterMenu", "active");
        return "dashboard";
    }

    @GetMapping("/si/monitoring")
    public String showSiMonitoring(HttpServletRequest request, ModelMap model){
//        UserFormModel user = (UserFormModel) request.getSession().getAttribute("userLogin");
//        model.addAttribute("user", user);
        model.addAttribute("siDeliverableHeader", "menu-open");
        model.addAttribute("siDeliverableMenu", "active");
        model.addAttribute("siMonitoringMenu", "active");
        return "dashboard";
    }

    @GetMapping("/si/alurdok")
    public String showSiAlurDok(HttpServletRequest request, ModelMap model){
//        UserFormModel user = (UserFormModel) request.getSession().getAttribute("userLogin");
//        model.addAttribute("user", user);
        model.addAttribute("siDeliverableHeader", "menu-open");
        model.addAttribute("siDeliverableMenu", "active");
        model.addAttribute("siAlurDokMenu", "active");
        return "dashboard";
    }

    @GetMapping("/pmqa/dashboard")
    public String showPmqaDashboard(HttpServletRequest request, ModelMap model){
//        UserFormModel user = (UserFormModel) request.getSession().getAttribute("userLogin");
//        model.addAttribute("user", user);
        model.addAttribute("pmqaDeliverableHeader", "menu-open");
        model.addAttribute("pmqaDeliverableMenu", "active");
        model.addAttribute("pmqaDashboardMenu", "active");
        return "dashboard";
    }

    @GetMapping("/pmqa/master")
    public String showPmqaMaster(HttpServletRequest request, ModelMap model){
//        UserFormModel user = (UserFormModel) request.getSession().getAttribute("userLogin");
//        model.addAttribute("user", user);
        model.addAttribute("pmqaDeliverableHeader", "menu-open");
        model.addAttribute("pmqaDeliverableMenu", "active");
        model.addAttribute("pmqaMasterMenu", "active");
        return "pmqa/deliverable/master";
    }

    @GetMapping("/pmqa/monitoring")
    public String showPmqaMonitoring(HttpServletRequest request, ModelMap model){
//        UserFormModel user = (UserFormModel) request.getSession().getAttribute("userLogin");
//        model.addAttribute("user", user);
        model.addAttribute("pmqaDeliverableHeader", "menu-open");
        model.addAttribute("pmqaDeliverableMenu", "active");
        model.addAttribute("pmqaMonitoringMenu", "active");
        return "pmqa/deliverable/monitoring";
    }

    @GetMapping("/pmqa/alurdok")
    public String showPmqaAlurDok(HttpServletRequest request, ModelMap model){
//        UserFormModel user = (UserFormModel) request.getSession().getAttribute("userLogin");
//        model.addAttribute("user", user);
        model.addAttribute("pmqaDeliverableHeader", "menu-open");
        model.addAttribute("pmqaDeliverableMenu", "active");
        model.addAttribute("pmqaAlurDokMenu", "active");
        return "dashboard";
    }

    @GetMapping("/cm/dashboard")
    public String showCmDashboard(HttpServletRequest request, ModelMap model){
//        UserFormModel user = (UserFormModel) request.getSession().getAttribute("userLogin");
//        model.addAttribute("user", user);
        model.addAttribute("cmDeliverableHeader", "menu-open");
        model.addAttribute("cmDeliverableMenu", "active");
        model.addAttribute("cmDashboardMenu", "active");
        return "dashboard";
    }

    @GetMapping("/cm/master")
    public String showCmMaster(HttpServletRequest request, ModelMap model){
//        UserFormModel user = (UserFormModel) request.getSession().getAttribute("userLogin");
//        model.addAttribute("user", user);
        model.addAttribute("cmDeliverableHeader", "menu-open");
        model.addAttribute("cmDeliverableMenu", "active");
        model.addAttribute("cmMasterMenu", "active");
        return "dashboard";
    }

    @GetMapping("/cm/monitoring")
    public String showCmMonitoring(HttpServletRequest request, ModelMap model){
//        UserFormModel user = (UserFormModel) request.getSession().getAttribute("userLogin");
//        model.addAttribute("user", user);
        model.addAttribute("cmDeliverableHeader", "menu-open");
        model.addAttribute("cmDeliverableMenu", "active");
        model.addAttribute("cmMonitoringMenu", "active");
        return "dashboard";
    }

    @GetMapping("/cm/alurdok")
    public String showCmAlurDok(HttpServletRequest request, ModelMap model){
//        UserFormModel user = (UserFormModel) request.getSession().getAttribute("userLogin");
//        model.addAttribute("user", user);
        model.addAttribute("cmDeliverableHeader", "menu-open");
        model.addAttribute("cmDeliverableMenu", "active");
        model.addAttribute("cmAlurDokMenu", "active");
        return "dashboard";
    }

    @GetMapping("/pengaturan/role")
    public String showPengaturanRole(HttpServletRequest request, ModelMap model){
//        UserFormModel user = (UserFormModel) request.getSession().getAttribute("userLogin");
//        model.addAttribute("user", user);
        model.addAttribute("roleMenu", "active");
        return "dashboard";
    }
}
