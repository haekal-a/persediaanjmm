package com.tamu.service.implementation;

import com.tamu.domain.model.KunjunganModel;
import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.table.Kunjungan;
import com.tamu.domain.table.User;
import com.tamu.repository.KunjunganRepo;
import com.tamu.service.IKunjunganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;

@Service
@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
public class KunjunganService implements IKunjunganService {

    @Autowired
    private KunjunganRepo kunjunganRepo;

    @Override
    public ResponseModel saveKunjungan(KunjunganModel kunjunganModel, User user) {
        ResponseModel res = new ResponseModel("Simpan Data Kunjungan");
        Kunjungan kunjungan = new Kunjungan();
        kunjungan.setIdMfwp(new BigDecimal(kunjunganModel.getIdWp()));
        kunjungan.setIdPegawai(new BigDecimal(kunjunganModel.getIdPegawai()));
        kunjungan.setKeperluan(kunjunganModel.getKeperluan());
        kunjungan.setFgStatus("1");
        kunjungan.setCreationDate(new Date());
        kunjungan.setCreatedBy(user.getUsername());
        kunjunganRepo.save(kunjungan);
        return res;
    }
}
