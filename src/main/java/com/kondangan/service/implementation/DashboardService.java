package com.kondangan.service.implementation;

import com.kondangan.domain.model.ResponseModel;
import com.kondangan.domain.model.datatables.mapping.DataTablesInput;
import com.kondangan.repository.DetailDocumentRepo;
import com.kondangan.repository.MonitoringRepo;
import com.kondangan.service.IDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
public class DashboardService implements IDashboardService {

    @Autowired
    private MonitoringRepo monitoringRepo;
    @Autowired
    private DetailDocumentRepo detailDocumentRepo;

    @Override
    public ResponseModel getDeliverable(DataTablesInput dataTablesInput) {
        return null;
    }

    @Override
    public ResponseModel getPieChart() {
        long prosesQa = monitoringRepo.countBySubmissionStatus("1");
        long perbaikan = monitoringRepo.countBySubmissionStatus("2");
        long disetujui = monitoringRepo.countBySubmissionStatus("3");
        List<String> label = new ArrayList<>();
        label.add("Dalam Proses QA");
        label.add("Perbaikan oleh Deloitte");
        label.add("Disetujui Steerco");
        List<Integer> data = new ArrayList<>();
        data.add((int) prosesQa);
        data.add((int) perbaikan);
        data.add((int) disetujui);
        HashMap deliverableStatus = new HashMap();
        deliverableStatus.put("deliverableLabel", label);
        deliverableStatus.put("deliverableData", data);

        long belumDibayar = monitoringRepo.countByPaymentStatus("1");
        long siapDibayar = monitoringRepo.countByPaymentStatus("2");
        long prosesBayar = monitoringRepo.countByPaymentStatus("3");
        long sudahBayar = monitoringRepo.countByPaymentStatus("4");
        label = new ArrayList<>();
        label.add("Belum Dapat Dibayarkan");
        label.add("Belum Ditagihkan");
        label.add("Proses Pembayaran");
        label.add("Sudah Dibayar");
        data = new ArrayList<>();
        data.add((int) belumDibayar);
        data.add((int) siapDibayar);
        data.add((int) prosesBayar);
        data.add((int) sudahBayar);
        deliverableStatus.put("paymentLabel", label);
        deliverableStatus.put("paymentData", data);
        ResponseModel res = new ResponseModel("Get Deliverable Status");
        res.setObject(deliverableStatus);
        return res;
    }
}
