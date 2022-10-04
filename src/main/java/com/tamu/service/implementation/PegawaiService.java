package com.tamu.service.implementation;

import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.table.Mfwp;
import com.tamu.domain.table.Pegawai;
import com.tamu.repository.PegawaiRepo;
import com.tamu.service.IPegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
public class PegawaiService implements IPegawaiService {
    @Autowired
    private PegawaiRepo pegawaiRepo;

    @Override
    public ResponseModel getAllPegawai() {
        ResponseModel<List<Pegawai>> res = new ResponseModel<>("Get Data WP");
        List<Pegawai> pegawais = pegawaiRepo.findAll();
        res.setObject(pegawais);
        return res;
    }
}
