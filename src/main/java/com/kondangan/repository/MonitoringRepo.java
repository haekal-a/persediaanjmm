package com.kondangan.repository;

import com.kondangan.domain.model.MonitoringCountModel;
import com.kondangan.domain.table.Monitoring;
import com.kondangan.service.common.RepoQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MonitoringRepo extends JpaRepository<Monitoring, BigDecimal> {
    Monitoring findByDeliverableCodeAndPeriod(String deliverableCode, Integer period);

    long countByPosition(Integer position);

    long countByPaymentStatus(String paymentStatus);

    @Query(RepoQuery.COUNT_TOTAL_MONITORING_BY_POSITION_GROUP_BY_PERIOD)
    List<MonitoringCountModel> countTotalMonitoringByPositionGroupByPeriod(Integer position);

    @Query(RepoQuery.COUNT_TOTAL_MONITORING_GROUP_BY_POSITION)
    List<MonitoringCountModel> countTotalMonitoringGroupByPosition();

    @Query(RepoQuery.COUNT_TOTAL_MONITORING_BY_PAYMENT_GROUP_BY_PERIOD)
    List<MonitoringCountModel> countTotalMonitoringByPaymentGroupByPeriod(Integer payment);

    @Query(RepoQuery.COUNT_TOTAL_MONITORING_GROUP_BY_PAYMENT)
    List<MonitoringCountModel> countTotalMonitoringGroupByPayment();
}
