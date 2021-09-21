package com.kondangan.service;

import com.kondangan.domain.model.MonitoringFormModel;
import com.kondangan.domain.model.ResponseModel;
import com.kondangan.domain.model.datatables.mapping.DataTablesInput;

public interface IMonitoringService {
    ResponseModel getDaftarMonitoring(DataTablesInput dataTablesInput);

    ResponseModel saveMonitoring(MonitoringFormModel monitoringFormModel, String idUser);

    ResponseModel deleteMonitoring(String idMonitoring);
}
