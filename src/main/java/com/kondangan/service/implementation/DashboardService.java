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
}
