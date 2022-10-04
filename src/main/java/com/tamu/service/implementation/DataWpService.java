package com.tamu.service.implementation;

import com.tamu.domain.model.ProfilWpModel;
import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.table.Mfwp;
import com.tamu.repository.DataWpRepo;
import com.tamu.service.ICetakanService;
import com.tamu.service.IDataWpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashMap;

@Service
@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
public class DataWpService implements IDataWpService {

    @Autowired
    private DataWpRepo dataWpRepo;

    @Autowired
    private ICetakanService cetakanService;

    @Override
    public ResponseModel getDataWp(String npwp) {
        ResponseModel<Mfwp> res = new ResponseModel<>("Get Data WP");
        Mfwp dataWp = dataWpRepo.getByNpwp15(npwp);
        res.setObject(dataWp);
        return res;
    }

    @Override
    public ResponseModel saveDataWp(ProfilWpModel profilWpModel) {
        ResponseModel res = new ResponseModel("Simpan Data WP");
        byte[] oldData, newData;
        Mfwp mfwp = dataWpRepo.getById(new BigDecimal(profilWpModel.getId()));
        if (mfwp.getJenisWp().equals("OP")){
            oldData = (byte[]) cetakanService.getCetakanWpOp(mfwp).getObject();
        } else if (mfwp.getJenisWp().equals("BADAN")){
            oldData = (byte[]) cetakanService.getCetakanWpBadan(mfwp).getObject();
        } else {
            oldData = (byte[]) cetakanService.getCetakanWpPemerintah(mfwp).getObject();
        }

        mfwp.setAlamat(profilWpModel.getAlamat());
        mfwp.setKelurahan(profilWpModel.getKelurahan());
        mfwp.setKecamatan(profilWpModel.getKecamatan());
        mfwp.setKota(profilWpModel.getKota());
        mfwp.setPropinsi(profilWpModel.getPropinsi());
        mfwp.setKodePos(profilWpModel.getKodePos());
        mfwp.setNomorTelepon(profilWpModel.getNomorTelepon());
        mfwp.setNomorFax(profilWpModel.getNomorFax());
        mfwp.setEmail(profilWpModel.getEmail());
        mfwp.setNomorIdentitas(profilWpModel.getNoIdentitas());
        mfwp.setTanggalLahir(profilWpModel.getTglLahir());
        dataWpRepo.save(mfwp);

        if (mfwp.getJenisWp().equals("OP")){
            newData = (byte[]) cetakanService.getCetakanWpOp(mfwp).getObject();
        } else if (mfwp.getJenisWp().equals("BADAN")){
            newData = (byte[]) cetakanService.getCetakanWpBadan(mfwp).getObject();
        } else {
            newData = (byte[]) cetakanService.getCetakanWpPemerintah(mfwp).getObject();
        }

        HashMap<String, byte[]> map = new HashMap<>();
        map.put("newData", newData);
        map.put("oldData", oldData);
        res.setObject(map);
        return res;
    }
}
