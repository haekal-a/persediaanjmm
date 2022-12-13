package com.tamu.service.implementation;

import com.tamu.domain.model.ProfilWpModel;
import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.table.DataWp;
import com.tamu.domain.table.Mfwp;
import com.tamu.domain.table.User;
import com.tamu.repository.DataWpRepo;
import com.tamu.repository.MfwpRepo;
import com.tamu.service.ICetakanService;
import com.tamu.service.IDataWpService;
import com.tamu.util.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
public class DataWpService implements IDataWpService {

    @Autowired
    private DataWpRepo dataWpRepo;
    @Autowired
    private MfwpRepo mfwpRepo;

    @Autowired
    private ICetakanService cetakanService;

    @Override
    public ResponseModel getDataWp(String npwp) {
        ResponseModel res = new ResponseModel<>("Get Data WP");
        Mfwp mfwp = mfwpRepo.getByNpwp15(npwp);
        if (Objects.isNull(mfwp)) {
            throw new AppException(1, "Data Tidak Ditemukan.");
        } else {
            if (mfwp.getStatusWp().contains("DE")){
                throw new AppException(1, "NPWP "+mfwp.getNpwp15()+" berstatus " + mfwp.getStatusWp());
            } else {
                DataWp dataWp = dataWpRepo.getByNpwp15(npwp);
                if (Objects.isNull(dataWp)){
                    res.setObject(mfwp);
                } else {
                    res.setObject(dataWp);
                }
            }
        }
        return res;
    }

    @Override
    public ResponseModel saveDataWp(ProfilWpModel profilWpModel, User user) {
        ResponseModel res = new ResponseModel("Simpan Data WP");
        byte[] oldData, newData;
        DataWp dataWp = dataWpRepo.getByNpwp15(profilWpModel.getNpwp15());
        if (Objects.isNull(dataWp)){
            Mfwp mfwp = mfwpRepo.getByNpwp15(profilWpModel.getNpwp15());
            dataWp = new DataWp();
            dataWp.setNpwp15(mfwp.getNpwp15());
            dataWp.setNamaWp(mfwp.getNamaWp());
            dataWp.setAlamat(mfwp.getAlamat());
            dataWp.setKelurahan(mfwp.getKelurahan());
            dataWp.setKecamatan(mfwp.getKecamatan());
            dataWp.setKota(mfwp.getKota());
            dataWp.setPropinsi(mfwp.getPropinsi());
            dataWp.setKodePos(mfwp.getKodePos());
            dataWp.setNomorTelepon(mfwp.getNomorTelepon());
            dataWp.setNomorFax(mfwp.getNomorFax());
            dataWp.setEmail(mfwp.getEmail());
            dataWp.setNomorIdentitas(mfwp.getNomorIdentitas());
            dataWp.setTanggalLahir(mfwp.getTanggalLahir());
            dataWp.setJenisWp(mfwp.getJenisWp());
            dataWp.setKodeKlu(mfwp.getKodeKlu());
            dataWp.setNamaKlu(mfwp.getNamaKlu());
            dataWp.setCreatedBy(user.getUsername());
            dataWp.setCreationDate(new Date());
        } else {
            dataWp.setLastUpdatedBy(user.getUsername());
            dataWp.setLastUpdatedDate(new Date());
        }

        if (dataWp.getJenisWp().equals("OP")){
            oldData = (byte[]) cetakanService.getCetakanWpOp(dataWp).getObject();
        } else if (dataWp.getJenisWp().equals("BADAN")){
            oldData = (byte[]) cetakanService.getCetakanWpBadan(dataWp).getObject();
        } else {
            oldData = (byte[]) cetakanService.getCetakanWpPemerintah(dataWp).getObject();
        }

        dataWp.setAlamat(profilWpModel.getAlamat());
        dataWp.setKelurahan(profilWpModel.getKelurahan());
        dataWp.setKecamatan(profilWpModel.getKecamatan());
        dataWp.setKota(profilWpModel.getKota());
        dataWp.setPropinsi(profilWpModel.getPropinsi());
        dataWp.setKodePos(profilWpModel.getKodePos());
        dataWp.setNomorTelepon(profilWpModel.getNomorTelepon());
        dataWp.setNomorFax(profilWpModel.getNomorFax());
        dataWp.setEmail(profilWpModel.getEmail());
        dataWp.setNomorIdentitas(profilWpModel.getNoIdentitas());
        dataWp.setTanggalLahir(profilWpModel.getTglLahir());
        dataWp.setKodeKlu(profilWpModel.getKlu());
        dataWp.setNamaKlu(profilWpModel.getKeteranganKlu());
        dataWpRepo.save(dataWp);

        if (dataWp.getJenisWp().equals("OP")){
            newData = (byte[]) cetakanService.getCetakanWpOp(dataWp).getObject();
        } else if (dataWp.getJenisWp().equals("BADAN")){
            newData = (byte[]) cetakanService.getCetakanWpBadan(dataWp).getObject();
        } else {
            newData = (byte[]) cetakanService.getCetakanWpPemerintah(dataWp).getObject();
        }

        HashMap<String, byte[]> map = new HashMap<>();
        map.put("newData", newData);
        map.put("oldData", oldData);
        res.setObject(map);
        return res;
    }
}
