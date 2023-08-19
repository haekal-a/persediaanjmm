package com.tamu.service.implementation;

import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.model.TransaksiModel;
import com.tamu.domain.model.datatables.mapping.DataTablesInput;
import com.tamu.domain.model.datatables.mapping.DataTablesOutput;
import com.tamu.domain.table.Persediaan;
import com.tamu.domain.table.Transaksi;
import com.tamu.domain.table.User;
import com.tamu.repository.PersediaanRepo;
import com.tamu.repository.TransaksiRepo;
import com.tamu.repository.repomodel.KunjunganRepoModel;
import com.tamu.repository.repomodel.MonitoringRepoModel;
import com.tamu.service.ITransaksiService;
import com.tamu.service.common.UtilityService;
import com.tamu.util.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
public class TransaksiService implements ITransaksiService {

    private final TransaksiRepo transaksiRepo;
    private final PersediaanRepo persediaanRepo;
    private final UtilityService utilityService;

    @Autowired
    public TransaksiService(TransaksiRepo transaksiRepo, PersediaanRepo persediaanRepo, UtilityService utilityService) {
        this.transaksiRepo = transaksiRepo;
        this.persediaanRepo = persediaanRepo;
        this.utilityService = utilityService;
    }

    @Override
    public ResponseModel getLastTransaction(String tgl, User user) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date tanggal = sdf.parse(tgl);
        List<Transaksi> transaksiList = transaksiRepo.getTransaksiByTanggalTransaksiOrderByCreationDateDesc(tanggal);
        String lastKodeTransaksi = null;
        if (transaksiList.size()>0) {
            lastKodeTransaksi = transaksiList.get(0).getKodeTransaksi().substring(9, 12);
        }
        int lastKodeBarang = lastKodeTransaksi == null ? 0 : Integer.parseInt(lastKodeTransaksi);
        String pattern="000";
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(lastKodeBarang+1);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyMMdd");
        String lastKodeTrx = sdf2.format(sdf.parse(tgl)) + output;
        ResponseModel res = new ResponseModel("Get Last Trx");
        res.setObject(lastKodeTrx);
        return res;
    }

    @Override
    public ResponseModel saveTransaksi(TransaksiModel transaksiModel, User user) throws AppException, ParseException {
        ResponseModel res = new ResponseModel("Simpan Data Referensi Barang");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Transaksi transaksi;

        Persediaan persediaan = persediaanRepo.getById(transaksiModel.getIdPersediaan());
        int hargaSatuan = persediaan.getHargaSatuan()==null?0: persediaan.getHargaSatuan();
        int hargaPerolehan = persediaan.getHargaPerolehan() == null ? 0 : persediaan.getHargaPerolehan();
        int jumlahBarang = persediaan.getJumlahBarang() == null ? 0 : persediaan.getJumlahBarang();
        int hargaSatuanTrx = Integer.parseInt(transaksiModel.getHargaSatuan());
        int jumlahBarangTrx = Integer.parseInt(transaksiModel.getJumlahBarang());

        if (transaksiModel.getIdTransaksi()==null||transaksiModel.getIdTransaksi().equals("")){
            transaksi = new Transaksi();
            transaksi.setCreatedBy(user.getUsername());
            transaksi.setCreationDate(new Date());
        } else {
            transaksi = transaksiRepo.getById(transaksiModel.getIdTransaksi());
            transaksi.setUpdatedBy(user.getUsername());
            transaksi.setUpdatedDate(new Date());
            if (transaksi.getJenisTransaksi()==1){
                hargaPerolehan -= transaksi.getHargaSatuan()*transaksi.getJumlahBarang();
                jumlahBarang -= transaksi.getJumlahBarang();
                hargaSatuan = hargaPerolehan/jumlahBarang;
            } else {
                hargaPerolehan += hargaSatuan*transaksi.getJumlahBarang();
                jumlahBarang += transaksi.getJumlahBarang();
            }
        }

        if (transaksiModel.getDdlJenisTransaksi().equals("1")){
            hargaPerolehan += hargaSatuanTrx*jumlahBarangTrx;
            jumlahBarang += jumlahBarangTrx;
            hargaSatuan = hargaPerolehan/jumlahBarang;
        } else {
            if (jumlahBarang<1 || jumlahBarang<jumlahBarangTrx){
                throw new AppException(AppException.LEVEL_INFO, "Transaksi melebihi jumlah barang tersedia");
            }
            hargaPerolehan -= hargaSatuan*jumlahBarangTrx;
            jumlahBarang -= jumlahBarangTrx;
        }
        persediaan.setJumlahBarang(jumlahBarang);
        persediaan.setHargaSatuan(hargaSatuan);
        persediaan.setHargaPerolehan(hargaPerolehan);
        persediaan.setUpdatedDate(new Date());
        persediaan.setUpdatedBy(user.getUsername());
        persediaanRepo.save(persediaan);

        transaksi.setKodeTransaksi(transaksiModel.getKodeTransaksi());
        transaksi.setIdPersediaan(transaksiModel.getIdPersediaan());
        transaksi.setTitle(transaksiModel.getTitle());
        transaksi.setJenisTransaksi(Integer.valueOf(transaksiModel.getDdlJenisTransaksi()));
        transaksi.setTanggalTransaksi(sdf.parse(transaksiModel.getTanggalTransaksi()));
        transaksi.setKeterangan(transaksiModel.getKeterangan());
        transaksi.setHargaSatuan(Integer.valueOf(transaksiModel.getHargaSatuan()));
        transaksi.setJumlahBarang(Integer.valueOf(transaksiModel.getJumlahBarang()));
        transaksi.setSisaBarang(jumlahBarang);
        transaksiRepo.save(transaksi);
        return res;
    }

    @Override
    public ResponseModel getListTransaksi(DataTablesInput dataTablesInput, User user) {
        String jenisTransaksi = dataTablesInput.getParam1().equals("")?null:dataTablesInput.getParam1();
        String jenisBarang = dataTablesInput.getParam2();
        String namaBarang = dataTablesInput.getParam3();
        String tglAwal = dataTablesInput.getParam4().equals("")?null:dataTablesInput.getParam4();
        String tglAkhir = dataTablesInput.getParam5().equals("")?null:dataTablesInput.getParam5();
        List<MonitoringRepoModel> monitoringRepoModels = transaksiRepo.getMonitoringTransaksi(jenisTransaksi, jenisBarang, namaBarang, tglAwal, tglAkhir);
        DataTablesOutput output = utilityService.dataTablesOutputWrapper(monitoringRepoModels, (long) monitoringRepoModels.size(), dataTablesInput.getDraw());
        ResponseModel<DataTablesOutput> res = new ResponseModel("Get List Monitoring Transaksi");
        res.setObject(output);
        return res;
    }

    @Override
    public ResponseModel getTransaksiById(String idTransaksi, User user) {
        Transaksi transaksi = transaksiRepo.findById(idTransaksi).get();
        ResponseModel res = new ResponseModel("Get Transaksi");
        res.setObject(transaksi);
        return res;
    }

    @Override
    public ResponseModel deleteTransaksi(String idTransaksi, User user) {
        Transaksi transaksi = transaksiRepo.findById(idTransaksi).get();

        Persediaan persediaan = persediaanRepo.getById(transaksi.getIdPersediaan());
        int hargaSatuan = persediaan.getHargaSatuan()==null?0: persediaan.getHargaSatuan();
        int hargaPerolehan = persediaan.getHargaPerolehan() == null ? 0 : persediaan.getHargaPerolehan();
        int jumlahBarang = persediaan.getJumlahBarang() == null ? 0 : persediaan.getJumlahBarang();
        if (transaksi.getJenisTransaksi()==1){
            hargaPerolehan -= transaksi.getHargaSatuan()*transaksi.getJumlahBarang();
            jumlahBarang -= transaksi.getJumlahBarang();
            hargaSatuan = hargaPerolehan/jumlahBarang;
        } else {
            hargaPerolehan += hargaSatuan*transaksi.getJumlahBarang();
            jumlahBarang += transaksi.getJumlahBarang();
        }
        persediaan.setJumlahBarang(jumlahBarang);
        persediaan.setHargaSatuan(hargaSatuan);
        persediaan.setHargaPerolehan(hargaPerolehan);
        persediaan.setUpdatedDate(new Date());
        persediaan.setUpdatedBy(user.getUsername());
        persediaanRepo.save(persediaan);

        transaksiRepo.delete(transaksi);
        return new ResponseModel("Delete Transaksi");
    }
}
