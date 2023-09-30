package com.tamu.service.implementation;

import com.tamu.domain.model.BarangModel;
import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.model.datatables.mapping.DataTablesInput;
import com.tamu.domain.model.datatables.mapping.DataTablesOutput;
import com.tamu.domain.table.Persediaan;
import com.tamu.domain.table.User;
import com.tamu.repository.PersediaanRepo;
import com.tamu.repository.repomodel.MonitoringRepoModel;
import com.tamu.service.IPersediaanService;
import com.tamu.service.common.UtilityService;
import com.tamu.util.AppException;
import com.tamu.util.Constanta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
public class PersediaanService implements IPersediaanService {

    private final PersediaanRepo persediaanRepo;
    private final UtilityService utilityService;

    @Autowired
    public PersediaanService(PersediaanRepo persediaanRepo, UtilityService utilityService) {
        this.persediaanRepo = persediaanRepo;
        this.utilityService = utilityService;
    }

    @Override
    public ResponseModel saveReferensiBarang(BarangModel barangModel, User user) {
        // lever user tidak dapat mengubah level
        if (user.getLevel().equals(Constanta.LEVEL_USER)){
            throw new AppException(1, "Not Authorized");
        }
        ResponseModel res = new ResponseModel("Simpan Data Referensi Barang");
        Persediaan persediaan;
        String firstJenisBarang = barangModel.getJenisBarang().substring(0,1);
        String lastKode = persediaanRepo.getLastKodeBarang(firstJenisBarang);
        int lastKodeBarang = lastKode == null ? 0 : Integer.parseInt(lastKode);
        String pattern="000";
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(lastKodeBarang+1);
        String kodeBarang = firstJenisBarang+output;

        if (barangModel.getIdPersediaan()== null || barangModel.getIdPersediaan().equals("")){
            persediaan = new Persediaan();
            persediaan.setCreatedBy(user.getUsername());
            persediaan.setCreationDate(new Date());
            persediaan.setKodeBarang(kodeBarang);
        }   else {
            persediaan = persediaanRepo.getById(barangModel.getIdPersediaan());
            persediaan.setUpdatedBy(user.getUsername());
            persediaan.setUpdatedDate(new Date());
        }

        persediaan.setNamaBarang(barangModel.getNamaBarang());
        persediaan.setJenisBarang(barangModel.getJenisBarang());
        persediaan.setMerk(barangModel.getMerk());
        persediaan.setKeterangan(barangModel.getKeterangan());
        persediaanRepo.save(persediaan);
        return res;
    }

    @Override
    public ResponseModel getListReferensiBarang(DataTablesInput dataTablesInput, User user) {
        List<Persediaan> persediaanList = persediaanRepo.findAll();
        DataTablesOutput output = utilityService.dataTablesOutputWrapper(persediaanList, (long) persediaanList.size(), dataTablesInput.getDraw());
        ResponseModel<DataTablesOutput> res = new ResponseModel("Daftar Barang");
        res.setObject(output);
        return res;
    }

    @Override
    public ResponseModel getListReferensiBarang(User user) {
        List<Persediaan> persediaanList = persediaanRepo.findAll();
        ResponseModel res = new ResponseModel("Get List Barang");
        res.setObject(persediaanList);
        return res;
    }

    @Override
    public ResponseModel getListBarangOrederByJumlahAsc(User user) {
        List<Persediaan> persediaanList = persediaanRepo.findAll().stream()
                .sorted(Comparator.comparingInt(Persediaan::getJumlahBarang))
                .collect(Collectors.toList());

        ResponseModel res = new ResponseModel("Get List Barang");
        res.setObject(persediaanList);
        return res;
    }

    @Override
    public ResponseModel getListBarangOrederByJumlahDesc(User user) {
        List<Persediaan> persediaanList = persediaanRepo.findAll().stream()
                .sorted(Comparator.comparingInt(Persediaan::getJumlahBarang)
                        .reversed())
                .collect(Collectors.toList());
        ResponseModel res = new ResponseModel("Get List Barang");
        res.setObject(persediaanList);
        return res;
    }

    @Override
    public ResponseModel deleteReferensiBarang(String id, String levelUserLogin) {
        // lever user tidak dapat mengubah level
        if (levelUserLogin.equals(Constanta.LEVEL_USER)){
            throw new AppException(1, "Not Authorized");
        }
        Persediaan persediaan = persediaanRepo.getById(id);
        if (persediaan.getJumlahBarang()== null){
            //do nothing
        } else if (persediaan.getJumlahBarang()>0){
            throw new AppException(1, "Persediaan Barang ini tidak dapat dihapus karena masih memiliki saldo");

        }
        persediaanRepo.delete(persediaan);
        return new ResponseModel("Delete Barang");
    }

    @Override
    public ResponseModel getAllMerk(User user) {
        List<String> listNamaBarang = persediaanRepo.getAllMerk();
        ResponseModel res = new ResponseModel("Get List Nama Barang");
        res.setObject(listNamaBarang);
        return res;
    }

    @Override
    public ResponseModel getAllJenisBarang(User user) {
        List<String> listJenisBarang = persediaanRepo.getAllJenisBarang();
        ResponseModel res = new ResponseModel("Get List Jenis Barang");
        res.setObject(listJenisBarang);
        return res;
    }

    @Override
    public ResponseModel getMonitoringPersediaan(DataTablesInput dataTablesInput, User user) {
        String jenisBarang = dataTablesInput.getParam1();
        String namaBarang = dataTablesInput.getParam2();
        String merk = dataTablesInput.getParam3();
        String jumlahBarangAwal = dataTablesInput.getParam4().equals("")?null:dataTablesInput.getParam4();
        String jumlahBarangAkhir = dataTablesInput.getParam5().equals("")?null:dataTablesInput.getParam5();
        String hargaSatuanAwal = dataTablesInput.getParam6().equals("")?null:dataTablesInput.getParam6();
        String hargaSatuanAkhir = dataTablesInput.getParam7().equals("")?null:dataTablesInput.getParam7();
        List<MonitoringRepoModel> monitoringRepoModels = persediaanRepo.getMonitoringPersediaan(jenisBarang, namaBarang, merk, jumlahBarangAwal, jumlahBarangAkhir, hargaSatuanAwal, hargaSatuanAkhir);
        DataTablesOutput output = utilityService.dataTablesOutputWrapper(monitoringRepoModels, (long) monitoringRepoModels.size(), dataTablesInput.getDraw());
        ResponseModel<DataTablesOutput> res = new ResponseModel("Get List Monitoring Persediaan");
        res.setObject(output);
        return res;
    }
}
