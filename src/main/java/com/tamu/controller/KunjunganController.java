package com.tamu.controller;

import com.tamu.domain.model.KunjunganModel;
import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.table.User;
import com.tamu.service.IKunjunganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/kunjungan")
public class KunjunganController {

    @Autowired
    private IKunjunganService kunjunganService;

    @PostMapping("/save")
    public ResponseModel saveKunjungan(@RequestBody KunjunganModel kunjunganModel, HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("userLogin");
        return kunjunganService.saveKunjungan(kunjunganModel, user);
    }
}
