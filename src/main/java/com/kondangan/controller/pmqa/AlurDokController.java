package com.kondangan.controller.pmqa;

import com.kondangan.domain.model.AlurDocFormModel;
import com.kondangan.domain.model.MonitoringFormModel;
import com.kondangan.domain.model.ResponseModel;
import com.kondangan.domain.model.datatables.mapping.DataTablesInput;
import com.kondangan.domain.table.User;
import com.kondangan.service.IDetailDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/pmqa/alurdok")
public class AlurDokController {

    @Autowired
    private IDetailDocumentService detailDocumentService;

    @GetMapping("/")
    public ResponseModel getDaftarAlurDok(DataTablesInput dataTablesInput, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userLogin");
        return detailDocumentService.getDaftarDetailDocument(dataTablesInput);
    }

    @PostMapping(value = "/")
    public ResponseModel saveAlurDok(@Valid final @RequestPart(value = "model") AlurDocFormModel alurDocFormModel,
                                     @RequestPart(value = "fileuploadSurat", required = false) MultipartFile fileUploadSurat,
                                     @RequestPart(value = "fileuploadNd", required = false) MultipartFile fileUploadNd,
                                     @RequestPart(value = "fileuploadNdPsiap", required = false) MultipartFile fileUploadNdPsiap,
                                     @RequestPart(value = "fileuploadBaSteerco", required = false) MultipartFile fileUploadBaSteerco,
                                     @RequestPart(value = "fileuploadPersetujuanSteerco", required = false) MultipartFile fileUploadPersetujuanSteerco,
                                     @RequestPart(value = "fileuploadSPemberitahuanPPK", required = false) MultipartFile fileUploadSPemberitahuanPPK,
                                     @RequestPart(value = "fileuploadBaKemajuan", required = false) MultipartFile fileUploadBaKemajuan,
                                     @RequestPart(value = "fileuploadBast", required = false) MultipartFile fileUploadBast,
                                     @RequestPart(value = "fileuploadBaPembayaran", required = false) MultipartFile fileUploadBaPembayaran,
                                     @RequestPart(value = "fileuploadTagihan", required = false) MultipartFile fileUploadTagihan,
                                     @RequestPart(value = "fileuploadNdPermohonanBayar", required = false) MultipartFile fileUploadNdPermohonanBayar,
                                     @RequestPart(value = "fileuploadSpp", required = false) MultipartFile fileUploadSpp,
                                     @RequestPart(value = "fileuploadSpm", required = false) MultipartFile fileUploadSpm,
                                     @RequestPart(value = "fileuploadSp2d", required = false) MultipartFile fileUploadSp2d,
                                     HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("userLogin");
        return detailDocumentService.saveDetailDocument(alurDocFormModel,
                fileUploadSurat, fileUploadNd,
                fileUploadNdPsiap, fileUploadBaSteerco, fileUploadPersetujuanSteerco, fileUploadSPemberitahuanPPK,
                fileUploadBaKemajuan, fileUploadBast, fileUploadBaPembayaran, fileUploadTagihan, fileUploadNdPermohonanBayar, fileUploadSpp,
                fileUploadSpm, fileUploadSp2d,
                user.getId().toString());
    }

    @DeleteMapping(value = "/")
    public ResponseModel deleteAlurDok(@RequestParam String idDetailDocument, HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("userLogin");
        return detailDocumentService.deleteDetailDocument(idDetailDocument);
    }

    @GetMapping("/getlist")
    public ResponseModel getListAlurDokByIdMonitoringAndPeriod(@RequestParam String idMonitoring, @RequestParam String period, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userLogin");
        return detailDocumentService.getListDetailDocumentByIdMonitoringAndPeriod(idMonitoring, period);
    }

    @GetMapping("/getdocumentfile")
    public ResponseModel getDocumentFileByIdDetailDocument(DataTablesInput dataTablesInput, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userLogin");
        return detailDocumentService.getDocumentFileByIdDetailDocument(dataTablesInput);
    }

    @GetMapping("/getlistdocumentfile")
    public ResponseModel getListDocumentFileByIdDetailDocument(@RequestParam String idDetailDocument, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userLogin");
        return detailDocumentService.getListDocumentFileByIdDetailDocument(idDetailDocument);
    }
}
