package com.tamu.repository;

import com.tamu.domain.table.Transaksi;
import com.tamu.repository.repomodel.MonitoringRepoModel;
import com.tamu.service.common.RepoQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransaksiRepo extends JpaRepository<Transaksi, String> {
    List<Transaksi> getTransaksiByTanggalTransaksiOrderByCreationDateDesc(Date tanggalTransaksi);

    @Query(value = RepoQuery.GET_MONITORING_TRANSAKSI, nativeQuery = true)
    List<MonitoringRepoModel> getMonitoringTransaksi(@Param("jenisTransaksi") String jenisTransaksi,
                                                     @Param("jenisBarang") String jenisBarang,
                                                     @Param("namaBarang") String namaBarang,
                                                     @Param("tglAwal") String tglAwal,
                                                     @Param("tglAkhir") String tglAkhir);
}
