package com.tamu.controller;

import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.table.User;
import com.tamu.service.IDataWpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/datawp")
public class DataWpController {

    @Autowired
    private IDataWpService dataWpService;

    @GetMapping("/get")
    public ResponseModel getDataWp(@RequestParam String npwp, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userLogin");
        return dataWpService.getDataWp(npwp);
    }
}
