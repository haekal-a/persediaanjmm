package com.tamu.repository;

import com.tamu.domain.table.Kunjungan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface KunjunganRepo extends JpaRepository<Kunjungan, BigDecimal> {
}
