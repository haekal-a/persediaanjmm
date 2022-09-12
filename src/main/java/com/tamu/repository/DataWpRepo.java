package com.tamu.repository;

import com.tamu.domain.table.Mfwp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface DataWpRepo extends JpaRepository<Mfwp, BigDecimal> {
    Mfwp getByNpwp15(String npwp);
}
