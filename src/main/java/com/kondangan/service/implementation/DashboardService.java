package com.kondangan.service.implementation;

import com.kondangan.domain.model.DashboardModel;
import com.kondangan.domain.model.MonitoringCountModel;
import com.kondangan.domain.model.ResponseModel;
import com.kondangan.domain.model.datatables.mapping.DataTablesInput;
import com.kondangan.domain.model.datatables.mapping.DataTablesOutput;
import com.kondangan.repository.DetailDocumentRepo;
import com.kondangan.repository.MonitoringRepo;
import com.kondangan.service.IDashboardService;
import com.kondangan.service.common.UtilityService;
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
    private UtilityService utilityService;

    @Override
    public ResponseModel getDeliverable(DataTablesInput dataTablesInput) {
        List<MonitoringCountModel> listCountDisetujui = monitoringRepo.countTotalMonitoringByPositionGroupByPeriod(3);
        List<MonitoringCountModel> listCountProses = monitoringRepo.countTotalMonitoringByPositionGroupByPeriod(1);
        List<MonitoringCountModel> listCountPerbaikan = monitoringRepo.countTotalMonitoringByPositionGroupByPeriod(2);
        List<MonitoringCountModel> listCountJumlahDeliverable = monitoringRepo.countTotalMonitoringByPositionGroupByPeriod(null);
        List<MonitoringCountModel> listCountTotalDeliverable = monitoringRepo.countTotalMonitoringGroupByPosition();

        DashboardModel rowDisetujui = new DashboardModel("Disetujui Steerco");
        DashboardModel rowProses = new DashboardModel("Dalam Proses QA");
        DashboardModel rowPerbaikan = new DashboardModel("Perbaikan oleh Deloitte");
        DashboardModel rowJumlah = new DashboardModel("Jumlah Deliverable");

        setDashboardModel(rowDisetujui, listCountDisetujui);
        setDashboardModel(rowProses, listCountProses);
        setDashboardModel(rowPerbaikan, listCountPerbaikan);
        setDashboardModel(rowJumlah, listCountJumlahDeliverable);

        int sum = 0;
        for (MonitoringCountModel countDeliverable : listCountTotalDeliverable){
            switch (countDeliverable.getPeriod()){
                case 3 :
                    rowDisetujui.setTotal(countDeliverable.getTotal());
                    sum += countDeliverable.getTotal().intValue();
                    break;
                case 1 :
                    rowProses.setTotal(countDeliverable.getTotal());
                    sum += countDeliverable.getTotal().intValue();
                    break;
                case 2 :
                    rowPerbaikan.setTotal(countDeliverable.getTotal());
                    sum += countDeliverable.getTotal().intValue();
                    break;
            }
            rowJumlah.setTotal((long) sum);

        }

        List<DashboardModel> dashboardModelList = new ArrayList<>();
        dashboardModelList.add(rowDisetujui);
        dashboardModelList.add(rowProses);
        dashboardModelList.add(rowPerbaikan);
        dashboardModelList.add(rowJumlah);

        DataTablesOutput output = utilityService.dataTablesOutputWrapper(dashboardModelList, (long) dashboardModelList.size(), dataTablesInput.getDraw());
        ResponseModel<DataTablesOutput> res = new ResponseModel("Get Dashboard Deliverable Completion");
        res.setObject(output);
        return res;
    }

    @Override
    public ResponseModel getPieChart() {
        long prosesQa = monitoringRepo.countByPosition(1);
        long perbaikan = monitoringRepo.countByPosition(2);
        long disetujui = monitoringRepo.countByPosition(3);
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

    @Override
    public ResponseModel getPayment(DataTablesInput dataTablesInput) {
        List<MonitoringCountModel> listCountBelumDibayar = monitoringRepo.countTotalMonitoringByPaymentGroupByPeriod(1);
        List<MonitoringCountModel> listCountBelumDitagih = monitoringRepo.countTotalMonitoringByPaymentGroupByPeriod(2);
        List<MonitoringCountModel> listCountProsesBayar = monitoringRepo.countTotalMonitoringByPaymentGroupByPeriod(3);
        List<MonitoringCountModel> listCountSudahBayar = monitoringRepo.countTotalMonitoringByPaymentGroupByPeriod(4);
        List<MonitoringCountModel> listCountTotal = monitoringRepo.countTotalMonitoringByPaymentGroupByPeriod(null);
        List<MonitoringCountModel> listCountTotalDeliverable = monitoringRepo.countTotalMonitoringGroupByPayment();

        DashboardModel rowBelumDibayar = new DashboardModel("Belum Dapat Dibayarkan");
        DashboardModel rowBelumDitagih = new DashboardModel("Belum Ditagihkan");
        DashboardModel rowProsesBayar = new DashboardModel("Proses Pembayaran");
        DashboardModel rowSudahBayar = new DashboardModel("Sudah Dibayar");
        DashboardModel rowTotal = new DashboardModel("Total");

        setDashboardModel(rowBelumDibayar, listCountBelumDibayar);
        setDashboardModel(rowBelumDitagih, listCountBelumDitagih);
        setDashboardModel(rowProsesBayar, listCountProsesBayar);
        setDashboardModel(rowSudahBayar, listCountSudahBayar);
        setDashboardModel(rowTotal, listCountTotal);

        int sum = 0;
        for (MonitoringCountModel countDeliverable : listCountTotalDeliverable){
            switch (countDeliverable.getPaymentStatus()){
                case "1" :
                    rowBelumDibayar.setTotal(countDeliverable.getTotal());
                    sum += countDeliverable.getTotal().intValue();
                    break;
                case "2" :
                    rowBelumDitagih.setTotal(countDeliverable.getTotal());
                    sum += countDeliverable.getTotal().intValue();
                    break;
                case "3" :
                    rowProsesBayar.setTotal(countDeliverable.getTotal());
                    sum += countDeliverable.getTotal().intValue();
                    break;
                case "4" :
                    rowSudahBayar.setTotal(countDeliverable.getTotal());
                    sum += countDeliverable.getTotal().intValue();
                    break;
            }
            rowTotal.setTotal((long) sum);

        }

        List<DashboardModel> dashboardModelList = new ArrayList<>();
        dashboardModelList.add(rowBelumDibayar);
        dashboardModelList.add(rowBelumDitagih);
        dashboardModelList.add(rowProsesBayar);
        dashboardModelList.add(rowSudahBayar);
        dashboardModelList.add(rowTotal);

        DataTablesOutput output = utilityService.dataTablesOutputWrapper(dashboardModelList, (long) dashboardModelList.size(), dataTablesInput.getDraw());
        ResponseModel<DataTablesOutput> res = new ResponseModel("Get Dashboard Payment Completion");
        res.setObject(output);
        return res;
    }

    private void setDashboardModel(DashboardModel dashboardModel, List<MonitoringCountModel> monitoringCountModelList) {
        for (MonitoringCountModel monitoringCountModel : monitoringCountModelList) {
            String periode = monitoringCountModel.getPeriod().toString().substring(4, 8);
            switch (periode) {
                case "2101":
                    dashboardModel.setM1(monitoringCountModel.getTotal());
                    break;
                case "2102":
                    dashboardModel.setM2(monitoringCountModel.getTotal());
                    break;
                case "2103":
                    dashboardModel.setM3(monitoringCountModel.getTotal());
                    break;
                case "2104":
                    dashboardModel.setM4(monitoringCountModel.getTotal());
                    break;
                case "2105":
                    dashboardModel.setM5(monitoringCountModel.getTotal());
                    break;
                case "2106":
                    dashboardModel.setM6(monitoringCountModel.getTotal());
                    break;
                case "2107":
                    dashboardModel.setM7(monitoringCountModel.getTotal());
                    break;
                case "2108":
                    dashboardModel.setM8(monitoringCountModel.getTotal());
                    break;
                case "2109":
                    dashboardModel.setM9(monitoringCountModel.getTotal());
                    break;
                case "2110":
                    dashboardModel.setM10(monitoringCountModel.getTotal());
                    break;
                case "2111":
                    dashboardModel.setM11(monitoringCountModel.getTotal());
                    break;
                case "2112":
                    dashboardModel.setM12(monitoringCountModel.getTotal());
                    break;
                case "2201":
                    dashboardModel.setM13(monitoringCountModel.getTotal());
                    break;
                case "2202":
                    dashboardModel.setM14(monitoringCountModel.getTotal());
                    break;
                case "2203":
                    dashboardModel.setM15(monitoringCountModel.getTotal());
                    break;
                case "2204":
                    dashboardModel.setM16(monitoringCountModel.getTotal());
                    break;
                case "2205":
                    dashboardModel.setM17(monitoringCountModel.getTotal());
                    break;
                case "2206":
                    dashboardModel.setM18(monitoringCountModel.getTotal());
                    break;
                case "2207":
                    dashboardModel.setM19(monitoringCountModel.getTotal());
                    break;
                case "2208":
                    dashboardModel.setM20(monitoringCountModel.getTotal());
                    break;
                case "2209":
                    dashboardModel.setM21(monitoringCountModel.getTotal());
                    break;
                case "2210":
                    dashboardModel.setM22(monitoringCountModel.getTotal());
                    break;
                case "2211":
                    dashboardModel.setM23(monitoringCountModel.getTotal());
                    break;
                case "2212":
                    dashboardModel.setM24(monitoringCountModel.getTotal());
                    break;
                case "2301":
                    dashboardModel.setM25(monitoringCountModel.getTotal());
                    break;
                case "2302":
                    dashboardModel.setM26(monitoringCountModel.getTotal());
                    break;
                case "2303":
                    dashboardModel.setM27(monitoringCountModel.getTotal());
                    break;
                case "2304":
                    dashboardModel.setM28(monitoringCountModel.getTotal());
                    break;
                case "2305":
                    dashboardModel.setM29(monitoringCountModel.getTotal());
                    break;
                case "2306":
                    dashboardModel.setM30(monitoringCountModel.getTotal());
                    break;
                case "2307":
                    dashboardModel.setM31(monitoringCountModel.getTotal());
                    break;
                case "2308":
                    dashboardModel.setM32(monitoringCountModel.getTotal());
                    break;
                case "2309":
                    dashboardModel.setM33(monitoringCountModel.getTotal());
                    break;
                case "2310":
                    dashboardModel.setM34(monitoringCountModel.getTotal());
                    break;
                case "2311":
                    dashboardModel.setM35(monitoringCountModel.getTotal());
                    break;
                case "2312":
                    dashboardModel.setM36(monitoringCountModel.getTotal());
                    break;
            }
        }
    }
}
