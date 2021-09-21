package com.kondangan.service;

import com.kondangan.domain.model.MasterFormModel;
import com.kondangan.domain.model.ResponseModel;
import com.kondangan.domain.model.datatables.mapping.DataTablesInput;

import java.math.BigDecimal;

public interface IMasterService {
    ResponseModel getDaftarMaster(DataTablesInput dataTablesInput);

    ResponseModel saveMaster(MasterFormModel masterFormModel, String idUser);

    ResponseModel deleteMaster(String idMaster);

    ResponseModel getMaster(String deliverableCode);
}
