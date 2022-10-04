package com.tamu.repository;

import com.tamu.domain.table.Pegawai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PegawaiRepo extends JpaRepository<Pegawai, BigDecimal> {
}
