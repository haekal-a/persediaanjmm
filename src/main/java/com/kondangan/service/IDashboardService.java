package com.kondangan.service;

import com.kondangan.domain.model.ResponseModel;
import com.kondangan.domain.model.datatables.mapping.DataTablesInput;

public interface IDashboardService {
    ResponseModel getDeliverable(DataTablesInput dataTablesInput);

    ResponseModel getPieChart();

    ResponseModel getPayment(DataTablesInput dataTablesInput);
}
