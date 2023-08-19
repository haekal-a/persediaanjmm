package com.tamu.controller;

import com.tamu.domain.model.BarangModel;
import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.model.datatables.mapping.DataTablesInput;
import com.tamu.domain.table.User;
import com.tamu.service.IPersediaanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/barang")
public class ReferensiBarangController {

    private final IPersediaanService persediaanService;

    @Autowired
    public ReferensiBarangController(IPersediaanService persediaanService) {
        this.persediaanService = persediaanService;
    }

    @PostMapping("/save")
    public ResponseModel saveReferensiBarang(@RequestBody BarangModel barangModel, HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("userLogin");
        return persediaanService.saveReferensiBarang(barangModel, user);
    }

    @GetMapping("/getlist")
    public ResponseModel getListReferensiBarang(DataTablesInput dataTablesInput, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userLogin");
        return persediaanService.getListReferensiBarang(dataTablesInput, user);
    }

    @DeleteMapping(value = "/del/")
    public ResponseModel deleteReferensiBarang(@RequestParam String idPersediaan, HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("userLogin");
        return persediaanService.deleteReferensiBarang(idPersediaan, user.getLevel());
    }

    @GetMapping("/getmonitoring")
    public ResponseModel getMonitoringPersediaan(DataTablesInput dataTablesInput, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userLogin");
        return persediaanService.getMonitoringPersediaan(dataTablesInput, user);
    }

    @GetMapping("/getallmerk")
    public ResponseModel getAllMerk(DataTablesInput dataTablesInput, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userLogin");
        return persediaanService.getAllMerk(user);
    }

    @GetMapping("/getmonsedikit")
    public ResponseModel getListBarangOrederByJumlahAsc(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userLogin");
        return persediaanService.getListBarangOrederByJumlahAsc(user);
    }

    @GetMapping("/getmonbanyak")
    public ResponseModel getListBarangOrederByJumlahDesc(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userLogin");
        return persediaanService.getListBarangOrederByJumlahDesc(user);
    }
}
