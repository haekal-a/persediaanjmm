package com.kondangan.repository;

import com.kondangan.domain.table.DocumentFile;
import com.kondangan.repository.repomodel.DocumentRepoModel;
import com.kondangan.service.common.RepoQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DocumentFileRepo extends JpaRepository<DocumentFile, BigDecimal> {
    DocumentFile findByIdDetailDocumentAndIdJenisDocument(String idDetailDocument, String idJenisDocument);
    List<DocumentFile> findAllByIdDetailDocument(String idDetailDocument);

    @Query(value = RepoQuery.DOCUMENT_FILE_GET_BY_ID_DETAIL_DOCUMENT)
    List<DocumentRepoModel> getDocumentFileByIdDetailDocument(@Param("idDetailDocument") String idDetailDocument);
}
