package com.kondangan.repository;

import com.kondangan.domain.table.DocumentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DocumentFileRepo extends JpaRepository<DocumentFile, BigDecimal> {
    DocumentFile findByIdDetailDocumentAndIdJenisDocument(String idDetailDocument, String idJenisDocument);
    List<DocumentFile> findAllByIdDetailDocument(String idDetailDocument);
}
