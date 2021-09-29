package com.kondangan.repository;

import com.kondangan.domain.table.Monitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface MonitoringRepo extends JpaRepository<Monitoring, BigDecimal> {
    Monitoring findByDeliverableCodeAndPeriod(String deliverableCode, Integer period);
}
