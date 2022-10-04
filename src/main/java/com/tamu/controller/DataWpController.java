package com.tamu.controller;

import com.tamu.domain.model.ProfilWpModel;
import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.table.User;
import com.tamu.service.ICetakanService;
import com.tamu.service.IDataWpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/datawp")
public class DataWpController {

    @Autowired
    private IDataWpService dataWpService;

    @Autowired
    private ICetakanService cetakanService;

    @GetMapping("/get")
    public ResponseModel getDataWp(@RequestParam String npwp, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userLogin");
        return dataWpService.getDataWp(npwp);
    }

    @PostMapping("/save")
    public ResponseModel saveDataWp(@RequestBody ProfilWpModel profilWpModel, HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("userLogin");
        return dataWpService.saveDataWp(profilWpModel);
    }
}
