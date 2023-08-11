package com.tamu.controller;

import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.table.Pegawai;
import com.tamu.domain.table.User;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transaksi")
public class TransaksiController {
    @GetMapping("/getlistbarang")
    public ResponseModel getDataPegawai(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userLogin");
        ResponseModel<List<ModelMap>> res = new ResponseModel<>("Get Data WP");
        List<ModelMap> listBarang = new ArrayList<>();

        ModelMap barang1 = new ModelMap();
        barang1.addAttribute("namaBarang", "Castrol Oil Matic 1L");
        barang1.addAttribute("jenisBarang", "Oli");
        barang1.addAttribute("Merk", "Castrol");
        barang1.addAttribute("jumlahBarang", 12);
        barang1.addAttribute("hargaSatuan", 54000);
        listBarang.add(barang1);

        barang1 = new ModelMap();
        barang1.addAttribute("namaBarang", "Bohlam Depan Honda Absolute Revo");
        barang1.addAttribute("jenisBarang", "Lampu");
        barang1.addAttribute("Merk", "Honda");
        barang1.addAttribute("jumlahBarang", 7);
        barang1.addAttribute("hargaSatuan", 25000);
        listBarang.add(barang1);

        barang1 = new ModelMap();
        barang1.addAttribute("namaBarang", "Bohlam Belakang Honda Absolute Revo");
        barang1.addAttribute("jenisBarang", "Lampu");
        barang1.addAttribute("Merk", "Honda");
        barang1.addAttribute("jumlahBarang", 7);
        barang1.addAttribute("hargaSatuan", 25000);
        listBarang.add(barang1);

        res.setObject(listBarang);
        return res;
    }
}
