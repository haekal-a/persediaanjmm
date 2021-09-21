package com.kondangan.repository;

import com.kondangan.domain.table.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface MasterRepo extends JpaRepository<Master, BigDecimal> {
    Master getByIdDeliverable(String idDeliverable);
}
