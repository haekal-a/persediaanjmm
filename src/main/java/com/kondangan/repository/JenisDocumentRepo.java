package com.kondangan.repository;

import com.kondangan.domain.table.JenisDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface JenisDocumentRepo extends JpaRepository<JenisDocument, BigDecimal> {
}
