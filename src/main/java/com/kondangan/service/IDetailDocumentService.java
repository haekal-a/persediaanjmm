package com.kondangan.service;

import com.kondangan.domain.model.AlurDocFormModel;
import com.kondangan.domain.model.ResponseModel;
import com.kondangan.domain.model.datatables.mapping.DataTablesInput;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IDetailDocumentService {
    ResponseModel saveDetailDocument(AlurDocFormModel alurDocFormModel, MultipartFile fileUploadSurat, MultipartFile fileUploadNd,
                                     MultipartFile fileUploadNdPsiap, MultipartFile fileUploadBaSteerco, MultipartFile fileUploadPersetujuanSteerco, MultipartFile fileUploadSPemberitahuanPPK, MultipartFile fileUploadBaKemajuan, MultipartFile fileUploadBast, MultipartFile fileUploadBaPembayaran, MultipartFile fileUploadTagihan, MultipartFile fileUploadNdPermohonanBayar, MultipartFile fileUploadSpp, MultipartFile fileUploadSpm, MultipartFile fileUploadSp2d,
                                     String idUser) throws IOException;

    ResponseModel deleteDetailDocument(String idDetailDocument) throws IOException;

    ResponseModel getDaftarDetailDocument(DataTablesInput dataTablesInput);

    ResponseModel getListDetailDocumentByIdMonitoringAndPeriod(String idMonitoring, String period);
}
