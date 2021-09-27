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
    public ResponseModel saveAlurDok(@Valid final @RequestPart("model") AlurDocFormModel alurDocFormModel,
                                     @RequestPart("fileUploadSurat") MultipartFile fileUploadSurat,
                                     @RequestPart("fileUploadNd") MultipartFile fileUploadNd,
                                     @RequestPart("fileUploadNdPsiap") MultipartFile fileUploadNdPsiap,
                                     @RequestPart("fileUploadBaSteerco") MultipartFile fileUploadBaSteerco,
                                     @RequestPart("fileUploadPersetujuanSteerco") MultipartFile fileUploadPersetujuanSteerco,
                                     @RequestPart("fileUploadSPemberitahuanPPK") MultipartFile fileUploadSPemberitahuanPPK,
                                     @RequestPart("fileUploadBaKemajuan") MultipartFile fileUploadBaKemajuan,
                                     @RequestPart("fileUploadBast") MultipartFile fileUploadBast,
                                     @RequestPart("fileUploadBaPembayaran") MultipartFile fileUploadBaPembayaran,
                                     @RequestPart("fileUploadTagihan") MultipartFile fileUploadTagihan,
                                     @RequestPart("fileUploadNdPermohonanBayar") MultipartFile fileUploadNdPermohonanBayar,
                                     @RequestPart("fileUploadSpp") MultipartFile fileUploadSpp,
                                     @RequestPart("fileUploadSpm") MultipartFile fileUploadSpm,
                                     @RequestPart("fileUploadSp2d") MultipartFile fileUploadSp2d,
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
}
