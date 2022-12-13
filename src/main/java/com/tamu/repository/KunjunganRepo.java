package com.tamu.repository;

import com.tamu.domain.table.Kunjungan;
import com.tamu.repository.repomodel.KunjunganRepoModel;
import com.tamu.service.common.RepoQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface KunjunganRepo extends JpaRepository<Kunjungan, String> {
    @Query(value = RepoQuery.GET_ALL_KUNJUNGAN, nativeQuery = true)
    List<KunjunganRepoModel> getListKunjungan(@Param("namaWp") String namaWp,
                                              @Param("npwp") String npwp,
                                              @Param("nip") String nip,
                                              @Param("tglAwal") String tglAwal,
                                              @Param("tglAkhir") String tglAkhir,
                                              @Param("status") String status);
}
