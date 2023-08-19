package com.tamu.controller;

import com.tamu.domain.table.User;
import com.tamu.service.common.SecurityService;
import com.tamu.service.common.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    UtilityService utilityService;

    @GetMapping({"/", "/dashboard"})
    public String showHome(HttpServletRequest request, ModelMap model) {
        User user = (User) request.getSession().getAttribute("userLogin");
        model.addAttribute("user", user);
        model.addAttribute("dashboardMenu", "active");
        return "home";
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

    @GetMapping("/monitoring/trx")
    public String showMonitoringTransaksi(HttpServletRequest request, ModelMap model){
        User user = (User) request.getSession().getAttribute("userLogin");
        model.addAttribute("user", user);
        model.addAttribute("monTransaksiMenu", "active");
        return "monitoring_transaksi";
    }

    @GetMapping("/monitoring/persediaan")
    public String showMonitoringPersediaan(HttpServletRequest request, ModelMap model){
        User user = (User) request.getSession().getAttribute("userLogin");
        model.addAttribute("user", user);
        model.addAttribute("monPersediaanMenu", "active");
        return "monitoring_persediaan";
    }

    @GetMapping("/transaksi")
    public String showTransaksi(HttpServletRequest request, ModelMap model){
        User user = (User) request.getSession().getAttribute("userLogin");
        model.addAttribute("user", user);
        model.addAttribute("transMenu", "active");
        return "transaksi";
    }

    @GetMapping("/transaksi/{idTransaksi}")
    public String showTransaksiWithParam(@PathVariable String idTransaksi, HttpServletRequest request, ModelMap model){
        idTransaksi = UtilityService.cleanParam(idTransaksi);
        User user = (User) request.getSession().getAttribute("userLogin");
        model.addAttribute("user", user);
        model.addAttribute("transMenu", "active");
        model.addAttribute("idTransaksi", idTransaksi);
        return "transaksi";
    }

    @GetMapping("/transaksibrg/{idPersediaan}")
    public String showTransaksiWithIdPersediaan(@PathVariable String idPersediaan, HttpServletRequest request, ModelMap model){
        idPersediaan = UtilityService.cleanParam(idPersediaan);
        User user = (User) request.getSession().getAttribute("userLogin");
        model.addAttribute("user", user);
        model.addAttribute("transMenu", "active");
        model.addAttribute("idPersediaan", idPersediaan);
        return "transaksi";
    }

    @GetMapping("/refbarang")
    public String showRefBarang(HttpServletRequest request, ModelMap model){
        User user = (User) request.getSession().getAttribute("userLogin");
        model.addAttribute("user", user);
        model.addAttribute("refMenu", "active");
        return "referensi_barang";
    }
}
