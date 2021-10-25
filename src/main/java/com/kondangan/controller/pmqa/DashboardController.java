package com.kondangan.controller.pmqa;

import com.kondangan.domain.model.ResponseModel;
import com.kondangan.domain.model.datatables.mapping.DataTablesInput;
import com.kondangan.domain.table.User;
import com.kondangan.service.IDashboardService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/pmqa/dashboard")
public class DashboardController {

    @Autowired
    private IDashboardService dashboardService;

    @GetMapping("/deliverable")
    public ResponseModel getDeliverable(DataTablesInput dataTablesInput, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userLogin");
        return dashboardService.getDeliverable(dataTablesInput);
    }

    @GetMapping("/payment")
    public ResponseModel getPayment(DataTablesInput dataTablesInput, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userLogin");
        return dashboardService.getPayment(dataTablesInput);
    }

    @GetMapping("/piechart")
    public ResponseModel getPieChart(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userLogin");
        return dashboardService.getPieChart();
    }
}
