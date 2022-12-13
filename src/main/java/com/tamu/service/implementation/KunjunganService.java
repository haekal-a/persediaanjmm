package com.tamu.service.implementation;

import com.tamu.domain.model.KunjunganModel;
import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.model.datatables.mapping.DataTablesInput;
import com.tamu.domain.model.datatables.mapping.DataTablesOutput;
import com.tamu.domain.table.Kunjungan;
import com.tamu.domain.table.User;
import com.tamu.repository.KunjunganRepo;
import com.tamu.repository.repomodel.KunjunganRepoModel;
import com.tamu.service.IKunjunganService;
import com.tamu.service.common.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
public class KunjunganService implements IKunjunganService {

    @Autowired
    private KunjunganRepo kunjunganRepo;

    @Autowired
    private UtilityService utilityService;

    @Override
    public ResponseModel saveKunjungan(KunjunganModel kunjunganModel, User user) {
        ResponseModel res = new ResponseModel("Simpan Data Kunjungan");
        Kunjungan kunjungan = new Kunjungan();
        kunjungan.setIdDatawp(kunjunganModel.getIdWp());
        kunjungan.setNpwp(kunjunganModel.getNpwp());
        kunjungan.setIdPegawai(kunjunganModel.getIdPegawai());
        kunjungan.setKeperluan(kunjunganModel.getKeperluan());
        kunjungan.setFgStatus("1");
        kunjungan.setCreationDate(new Date());
        kunjungan.setCreatedBy(user.getUsername());
        kunjunganRepo.save(kunjungan);
        return res;
    }

    @Override
    public ResponseModel getListKunjungan(DataTablesInput dataTablesInput, User user) {
        String namaWp = dataTablesInput.getParam1().equals("")?null:'%'+dataTablesInput.getParam1()+'%';
        String npwp = dataTablesInput.getParam2().equals("")?null:dataTablesInput.getParam2();
        String nip = dataTablesInput.getParam3().equals("")?null:dataTablesInput.getParam3();
        String tglAwal = dataTablesInput.getParam4().equals("")?null:dataTablesInput.getParam4();
        String tglAkhir = dataTablesInput.getParam5().equals("")?null:dataTablesInput.getParam5();
        String status = dataTablesInput.getParam6().equals("")?null:dataTablesInput.getParam6();
        List<KunjunganRepoModel> kunjunganRepoModelList = kunjunganRepo.getListKunjungan(namaWp, npwp, nip, tglAwal, tglAkhir, status);
        DataTablesOutput output = utilityService.dataTablesOutputWrapper(kunjunganRepoModelList, (long) kunjunganRepoModelList.size(), dataTablesInput.getDraw());
        ResponseModel<DataTablesOutput> res = new ResponseModel("Get List Kunjungan");
        res.setObject(output);
        return res;
    }

    @Override
    public ResponseModel updateKunjungan(String id, String status, String ket, User user) {
        ResponseModel res = new ResponseModel("Update Kunjungan");
        Kunjungan kunjungan = kunjunganRepo.getById(id);
        kunjungan.setFgStatus(status);
        kunjungan.setKeterangan(ket);
        kunjungan.setLastUpdatedBy(user.getUsername());
        kunjungan.setLastUpdatedDate(new Date());
        kunjunganRepo.save(kunjungan);
        return res;
    }
}
