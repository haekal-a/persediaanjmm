package com.tamu.controller;

import com.tamu.domain.model.KunjunganModel;
import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.model.datatables.mapping.DataTablesInput;
import com.tamu.domain.table.User;
import com.tamu.service.IKunjunganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/update")
    public ResponseModel updateKunjungan(@RequestParam("id") String id, @RequestParam("status") String status, @RequestParam("ket") String ket, HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("userLogin");
        return kunjunganService.updateKunjungan(id, status, ket, user);
    }

    @GetMapping("/")
    public ResponseModel getListKunjungan(DataTablesInput dataTablesInput, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userLogin");
        return kunjunganService.getListKunjungan(dataTablesInput, user);
    }
}
