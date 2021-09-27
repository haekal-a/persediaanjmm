package com.kondangan.service.implementation;

import com.kondangan.domain.model.MonitoringFormModel;
import com.kondangan.domain.model.ResponseModel;
import com.kondangan.domain.model.datatables.mapping.DataTablesInput;
import com.kondangan.domain.model.datatables.mapping.DataTablesOutput;
import com.kondangan.domain.table.DetailDocument;
import com.kondangan.domain.table.Monitoring;
import com.kondangan.repository.DetailDocumentRepo;
import com.kondangan.repository.MonitoringRepo;
import com.kondangan.service.IMonitoringService;
import com.kondangan.service.common.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
public class MonitoringService implements IMonitoringService {

    @Autowired
    private MonitoringRepo monitoringRepo;

    @Autowired
    private UtilityService utilityService;

    @Autowired
    private DetailDocumentRepo detailDocumentRepo;

    @Override
    public ResponseModel getDaftarMonitoring(DataTablesInput dataTablesInput) {
        List<Monitoring> monitoring = monitoringRepo.findAll();
        DataTablesOutput output = utilityService.dataTablesOutputWrapper(monitoring, (long) monitoring.size(), dataTablesInput.getDraw());
        ResponseModel<DataTablesOutput> res = new ResponseModel("Daftar Monitoring");
        res.setObject(output);
        return res;
    }

    @Override
    public ResponseModel saveMonitoring(MonitoringFormModel monitoringFormModel, String idUser) {
        Monitoring monitoring;
        if (Objects.equals(monitoringFormModel.getIdMonitoring(), "")){
            monitoring = new Monitoring();
            monitoring.setCreatedBy(idUser);
            monitoring.setCreationDate(new Date());
        } else {
            monitoring = monitoringRepo.getById(new BigDecimal(monitoringFormModel.getIdMonitoring()));
            monitoring.setUpdatedBy(idUser);
            monitoring.setUpdatedDate(new Date());
        }
        monitoring.setSectionNo(monitoringFormModel.getSectionNo());
        monitoring.setFunction(monitoringFormModel.getFunction());
        monitoring.setTaskNo(monitoringFormModel.getTaskNo());
        monitoring.setTask(monitoringFormModel.getTask());
        monitoring.setDeliverableName(monitoringFormModel.getDeliverableName());
        monitoring.setScheduleInTor(monitoringFormModel.getScheduleInTor());
        monitoring.setDeliverableCode(monitoringFormModel.getDeliverableCode());
        monitoring.setMonthSubmission(monitoringFormModel.getMonthSubmission());
        monitoring.setSubmissionStatus(monitoringFormModel.getSubmissionStatus());
        monitoring.setPeriod(monitoringFormModel.getPeriod());
        monitoring.setLatestVersion(monitoringFormModel.getLatestVersion());
        monitoring.setPosition(monitoringFormModel.getPosition());
        monitoring.setSoftcopyStatus(monitoringFormModel.getSoftcopyStatus());
        monitoring.setHardcopyStatus(monitoringFormModel.getHardcopyStatus());
        monitoring.setHardcopyIn(monitoringFormModel.getHardcopyIn());
        monitoring.setHardcopyOut(monitoringFormModel.getHardcopyOut());
        monitoring.setHardcopyLeft(monitoringFormModel.getHardcopyLeft());
        monitoring.setPaymentStatus(monitoringFormModel.getPaymentStatus());
        monitoring.setKeterangan(monitoringFormModel.getKeterangan());
        monitoringRepo.save(monitoring);
        return new ResponseModel("Save Monitoring");
    }

    @Override
    public ResponseModel deleteMonitoring(String idMonitoring) {
//        List<DetailDocument> detailDocuments = detailDocumentRepo.
        Monitoring monitoring = monitoringRepo.getById(new BigDecimal(idMonitoring));
        monitoringRepo.delete(monitoring);
        return new ResponseModel("Delete Monitoring");
    }

    @Override
    public ResponseModel getListMonitoring() {
        List<Monitoring> monitoringList = monitoringRepo.findAll();
        ResponseModel res = new ResponseModel("Get List Monitoring");
        res.setObject(monitoringList);
        return res;
    }
}
