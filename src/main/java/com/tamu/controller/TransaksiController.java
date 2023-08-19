package com.tamu.controller;

import com.tamu.domain.model.BarangModel;
import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.model.TransaksiModel;
import com.tamu.domain.model.datatables.mapping.DataTablesInput;
import com.tamu.domain.table.User;
import com.tamu.service.IPersediaanService;
import com.tamu.service.ITransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/transaksi")
public class TransaksiController {
    private final IPersediaanService persediaanService;
    private final ITransaksiService transaksiService;

    @Autowired
    public TransaksiController(IPersediaanService persediaanService, ITransaksiService transaksiService) {
        this.persediaanService = persediaanService;
        this.transaksiService = transaksiService;
    }

    @GetMapping("/getlistbarang")
    public ResponseModel getListReferensiBarang(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userLogin");
        /*ResponseModel<List<ModelMap>> res = new ResponseModel<>("Get Data WP");
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

        res.setObject(listBarang);*/
        return persediaanService.getListReferensiBarang(user);
    }

    @GetMapping("/getlasttrx")
    public ResponseModel getLastTransaction(@RequestParam String tgl, HttpServletRequest request) throws ParseException {
        User user = (User) request.getSession().getAttribute("userLogin");
        return transaksiService.getLastTransaction(tgl, user);
    }

    @PostMapping("/save")
    public ResponseModel saveTransaksi(@RequestBody TransaksiModel transaksiModel, HttpServletRequest request) throws IOException, ParseException {
        User user = (User) request.getSession().getAttribute("userLogin");
        return transaksiService.saveTransaksi(transaksiModel, user);
    }

    @GetMapping("/getlist")
    public ResponseModel getListTransaksi(DataTablesInput dataTablesInput, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userLogin");
        return transaksiService.getListTransaksi(dataTablesInput, user);
    }

    @GetMapping("/getalljenisbarang")
    public ResponseModel getAllJenisBarang(DataTablesInput dataTablesInput, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userLogin");
        return persediaanService.getAllJenisBarang(user);
    }

    @GetMapping("/getbyid")
    public ResponseModel getTransaksiById(@RequestParam String idTransaksi, HttpServletRequest request) throws ParseException {
        User user = (User) request.getSession().getAttribute("userLogin");
        return transaksiService.getTransaksiById(idTransaksi, user);
    }

    @DeleteMapping(value = "/del/")
    public ResponseModel deleteTransaksi(@RequestParam String idTransaksi, HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("userLogin");
        return transaksiService.deleteTransaksi(idTransaksi, user);
    }
}
