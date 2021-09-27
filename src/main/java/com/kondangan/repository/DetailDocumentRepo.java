package com.kondangan.repository;

import com.kondangan.domain.table.DetailDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DetailDocumentRepo extends JpaRepository<DetailDocument, BigDecimal> {
    List<DetailDocument> findAllByIdMonitoringAndPeriod(String idMonitoring, Integer period);
}
