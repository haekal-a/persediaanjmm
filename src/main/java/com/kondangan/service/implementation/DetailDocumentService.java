package com.kondangan.service.implementation;

import com.kondangan.domain.model.AlurDocFormModel;
import com.kondangan.domain.model.ResponseModel;
import com.kondangan.domain.model.datatables.mapping.DataTablesInput;
import com.kondangan.domain.model.datatables.mapping.DataTablesOutput;
import com.kondangan.domain.table.DetailDocument;
import com.kondangan.domain.table.Monitoring;
import com.kondangan.repository.DetailDocumentRepo;
import com.kondangan.repository.MonitoringRepo;
import com.kondangan.service.IDetailDocumentService;
import com.kondangan.service.common.UtilityService;
import com.kondangan.util.Constanta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
public class DetailDocumentService implements IDetailDocumentService {

    @Autowired
    private UtilityService utilityService;

    @Autowired
    private DetailDocumentRepo detailDocumentRepo;

    @Autowired
    private MonitoringRepo monitoringRepo;

    @Override
    public ResponseModel saveDetailDocument(AlurDocFormModel alurDocFormModel, MultipartFile fileUploadSurat, MultipartFile fileUploadNd,
                                            MultipartFile fileUploadNdPsiap, MultipartFile fileUploadBaSteerco, MultipartFile fileUploadPersetujuanSteerco, MultipartFile fileUploadSPemberitahuanPPK, MultipartFile fileUploadBaKemajuan, MultipartFile fileUploadBast, MultipartFile fileUploadBaPembayaran, MultipartFile fileUploadTagihan, MultipartFile fileUploadNdPermohonanBayar, MultipartFile fileUploadSpp, MultipartFile fileUploadSpm, MultipartFile fileUploadSp2d,
                                            String idUser) throws IOException {
        DetailDocument detailDocument;
        if(Objects.equals(alurDocFormModel.getIdDetailDocument(), "")){
            detailDocument= new DetailDocument();
            detailDocument.setCreatedBy(idUser);
            detailDocument.setCreationDate(new Date());
            detailDocument.setIdDeliverable(alurDocFormModel.getIdDeliverable());
            detailDocument.setIdMonitoring(alurDocFormModel.getIdMonitoring());
            detailDocument.setPeriod(alurDocFormModel.getPeriod());
            detailDocument.setVersion(alurDocFormModel.getVersion());
        } else {
            detailDocument = detailDocumentRepo.getById(new BigDecimal(alurDocFormModel.getIdDetailDocument()));
            detailDocument.setUpdatedBy(idUser);
            detailDocument.setUpdatedDate(new Date());
        }

        detailDocument.setVersionName(alurDocFormModel.getVersionName());
        detailDocument.setTglSubmission(alurDocFormModel.getTglSubmission());
        detailDocument.setSuratDeloitte(alurDocFormModel.getSuratDeloitte());
        detailDocument.setTglSurat(alurDocFormModel.getTglSurat());
        detailDocument.setNdPenerusanPpk(alurDocFormModel.getNdPenerusanPpk());
        detailDocument.setTglNd(alurDocFormModel.getTglNd());
        detailDocument.setFlagPsiap(alurDocFormModel.getFlagPsiap());
        detailDocument.setNdPsiap(alurDocFormModel.getNdPsiap());
        detailDocument.setTglNdPsiap(alurDocFormModel.getTglNdPsiap());
        detailDocument.setBaSteerco(alurDocFormModel.getBaSteerco());
        detailDocument.setTglBaSteerco(alurDocFormModel.getTglBaSteerco());
        detailDocument.setNoApproveSteerco(alurDocFormModel.getNoApproveSteerco());
        detailDocument.setTglApprove(alurDocFormModel.getTglApprove());
        detailDocument.setSPemberitahuanPpk(alurDocFormModel.getSPemberitahuanPpk());
        detailDocument.setTglS(alurDocFormModel.getTglS());
        detailDocument.setBaKemajuan(alurDocFormModel.getBaKemajuan());
        detailDocument.setTglBaKemajuan(alurDocFormModel.getTglBaKemajuan());
        detailDocument.setBast(alurDocFormModel.getBast());
        detailDocument.setTglBast(alurDocFormModel.getTglBast());
        detailDocument.setBaPembayaran(alurDocFormModel.getBaPembayaran());
        detailDocument.setTglBaPembayaran(alurDocFormModel.getTglBaPembayaran());
        detailDocument.setTagihan(alurDocFormModel.getTagihan());
        detailDocument.setTglTagihan(alurDocFormModel.getTglTagihan());
        detailDocument.setNdPermohonanBayar(alurDocFormModel.getNdPermohonanBayar());
        detailDocument.setTglNdPermohonanBayar(alurDocFormModel.getTglNdPermohonanBayar());
        detailDocument.setSpp(alurDocFormModel.getSpp());
        detailDocument.setTglSpp(alurDocFormModel.getTglSpp());
        detailDocument.setSpm(alurDocFormModel.getSpm());
        detailDocument.setTglSpm(alurDocFormModel.getTglSpm());
        detailDocument.setSp2D(alurDocFormModel.getSp2D());
        detailDocument.setTglSp2D(alurDocFormModel.getTglSp2D());
        detailDocument.setStatusDeliverable(alurDocFormModel.getStatusDeliverable());
        detailDocument.setStatusPembayaran(alurDocFormModel.getStatusPembayaran());
        DetailDocument detailDoc = detailDocumentRepo.save(detailDocument);

        // save Doc File
        utilityService.saveFile(fileUploadSurat, detailDoc.getIdDetailDocument().toString(), Constanta.ID_SURATDELOITTE);
        utilityService.saveFile(fileUploadNd, detailDoc.getIdDetailDocument().toString(), Constanta.ID_NDPENERUSANPPK);
        utilityService.saveFile(fileUploadNdPsiap, detailDoc.getIdDetailDocument().toString(), Constanta.ID_NDPSIAP);
        utilityService.saveFile(fileUploadBaSteerco, detailDoc.getIdDetailDocument().toString(), Constanta.ID_BASTEERCO);
        utilityService.saveFile(fileUploadPersetujuanSteerco, detailDoc.getIdDetailDocument().toString(), Constanta.ID_APPROVESTEERCO);
        utilityService.saveFile(fileUploadSPemberitahuanPPK, detailDoc.getIdDetailDocument().toString(), Constanta.ID_SPEMBERITAHUANPPK);
        utilityService.saveFile(fileUploadBaKemajuan, detailDoc.getIdDetailDocument().toString(), Constanta.ID_BAKEMAJUAN);
        utilityService.saveFile(fileUploadBast, detailDoc.getIdDetailDocument().toString(), Constanta.ID_BAST);
        utilityService.saveFile(fileUploadBaPembayaran, detailDoc.getIdDetailDocument().toString(), Constanta.ID_BAPEMBAYARAN);
        utilityService.saveFile(fileUploadTagihan, detailDoc.getIdDetailDocument().toString(), Constanta.ID_TAGIHAN);
        utilityService.saveFile(fileUploadNdPermohonanBayar, detailDoc.getIdDetailDocument().toString(), Constanta.ID_NDPERMOHONANBAYAR);
        utilityService.saveFile(fileUploadSpp, detailDoc.getIdDetailDocument().toString(), Constanta.ID_SPP);
        utilityService.saveFile(fileUploadSpm, detailDoc.getIdDetailDocument().toString(), Constanta.ID_SPM);
        utilityService.saveFile(fileUploadSp2d, detailDoc.getIdDetailDocument().toString(), Constanta.ID_SP2D);

        // update monitoring
        Monitoring monitoring = monitoringRepo.getById(new BigDecimal(detailDoc.getIdMonitoring()));
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(detailDoc.getTglSubmission());
        monitoring.setMonthSubmission(strDate);
        monitoring.setLatestVersion(detailDoc.getVersionName());
        monitoring.setPaymentStatus(detailDoc.getStatusPembayaran());
        monitoring.setUpdatedDate(new Date());
        monitoring.setUpdatedBy(idUser);

        return new ResponseModel("Save Detail Document");
    }

    @Override
    public ResponseModel deleteDetailDocument(String idDetailDocument) throws IOException {
        DetailDocument detailDocument = detailDocumentRepo.getById(new BigDecimal(idDetailDocument));
        utilityService.deleteFile(detailDocument.getIdDetailDocument().toString());
        detailDocumentRepo.delete(detailDocument);
        return new ResponseModel("Delete Detail Document");
    }

    @Override
    public ResponseModel getDaftarDetailDocument(DataTablesInput dataTablesInput) {
        List<DetailDocument> detailDocuments = detailDocumentRepo.findAll();
        DataTablesOutput output = utilityService.dataTablesOutputWrapper(detailDocuments, (long) detailDocuments.size(), dataTablesInput.getDraw());
        ResponseModel<DataTablesOutput> res = new ResponseModel("Daftar Detail Document");
        res.setObject(output);
        return res;
    }

    @Override
    public ResponseModel getListDetailDocumentByIdMonitoringAndPeriod(String idMonitoring, String period) {
        List<DetailDocument> detailDocuments = detailDocumentRepo.findAllByIdMonitoringAndPeriod(idMonitoring, Integer.parseInt(period));
        ResponseModel res = new ResponseModel("Get List Detail Document");
        res.setObject(detailDocuments);
        return res;
    }
}
