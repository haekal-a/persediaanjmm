package com.kondangan.controller.pmqa;

import com.kondangan.domain.model.MonitoringFormModel;
import com.kondangan.domain.model.ResponseModel;
import com.kondangan.domain.model.datatables.mapping.DataTablesInput;
import com.kondangan.domain.table.User;
import com.kondangan.service.IMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/pmqa/monitoring")
public class MonitoringController {

    @Autowired
    private IMonitoringService monitoringService;

    @GetMapping("/")
    public ResponseModel getDaftarMonitoring(DataTablesInput dataTablesInput, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userLogin");
        return monitoringService.getDaftarMonitoring(dataTablesInput);
    }

    @PostMapping(value = "/")
    public ResponseModel saveMonitoring(@Valid final MonitoringFormModel monitoringFormModel, HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("userLogin");
        return monitoringService.saveMonitoring(monitoringFormModel, user.getId().toString());
    }

    @DeleteMapping(value = "/")
    public ResponseModel deleteMonitoring(@RequestParam String idMonitoring, HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("userLogin");
        return monitoringService.deleteMonitoring(idMonitoring);
    }
}
