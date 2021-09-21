package com.kondangan.service.implementation;

import com.kondangan.domain.model.MasterFormModel;
import com.kondangan.domain.model.ResponseModel;
import com.kondangan.domain.model.datatables.mapping.DataTablesInput;
import com.kondangan.domain.model.datatables.mapping.DataTablesOutput;
import com.kondangan.domain.table.Master;
import com.kondangan.repository.MasterRepo;
import com.kondangan.service.IMasterService;
import com.kondangan.service.common.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
public class MasterService implements IMasterService {

    @Autowired
    private MasterRepo masterRepo;

    @Autowired
    private UtilityService utilityService;

    @Override
    public ResponseModel getDaftarMaster(DataTablesInput dataTablesInput) {
        List<Master> masters = masterRepo.findAll();
        DataTablesOutput output = utilityService.dataTablesOutputWrapper(masters, (long) masters.size(), dataTablesInput.getDraw());
        ResponseModel<DataTablesOutput> res = new ResponseModel("Daftar Master");
        res.setObject(output);
        return res;
    }

    @Override
    public ResponseModel saveMaster(MasterFormModel masterFormModel, String idUser) {
        Master master;
        if (Objects.equals(masterFormModel.getIdMaster(), "")){
            master = new Master();
            master.setCreatedBy(idUser);
            master.setCreationDate(new Date());
        } else {
            master = masterRepo.getById(new BigDecimal(masterFormModel.getIdMaster()));
            master.setUpdatedBy(idUser);
            master.setUpdatedDate(new Date());
        }
        master.setIdDeliverable(masterFormModel.getIdDeliverable());
        master.setSectionNo(masterFormModel.getSectionNo());
        master.setFunction(masterFormModel.getFunction());
        master.setTask(masterFormModel.getTask());
        master.setTaskNo(masterFormModel.getTaskNo());
        master.setDeliverableName(masterFormModel.getDeliverableName());
        master.setScheduleInTor(masterFormModel.getScheduleInTor());
        masterRepo.save(master);
        return new ResponseModel("Save Master");
    }

    @Override
    public ResponseModel deleteMaster(String idMaster) {
        Master master = masterRepo.getById(new BigDecimal(idMaster));
        masterRepo.delete(master);
        return new ResponseModel("Delete Master");
    }

    @Override
    public ResponseModel getMaster(String deliverableCode) {
        ResponseModel res = new ResponseModel("Get Master");
        Master master = masterRepo.getByIdDeliverable(deliverableCode);
        res.setObject(master);
        return res;
    }
}
