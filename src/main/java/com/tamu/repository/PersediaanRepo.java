package com.tamu.repository;

import com.tamu.domain.table.Persediaan;

import com.tamu.repository.repomodel.MonitoringRepoModel;
import com.tamu.service.common.RepoQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersediaanRepo extends JpaRepository<Persediaan, String> {

    @Query(value = RepoQuery.GET_LAST_KODE_BARANG, nativeQuery = true)
    String getLastKodeBarang(@Param("jenisBarang") String jenisBarang);

    @Query(value = RepoQuery.GET_ALL_JENIS_BARANG, nativeQuery = true)
    List<String> getAllJenisBarang();

    @Query(value = RepoQuery.GET_ALL_MERK, nativeQuery = true)
    List<String> getAllMerk();

    @Query(value = RepoQuery.GET_MONITORING_PERSEDIAAN, nativeQuery = true)
    List<MonitoringRepoModel> getMonitoringPersediaan(@Param("jenisBarang") String jenisBarang,
                                                     @Param("namaBarang") String namaBarang,
                                                      @Param("merk") String merk,
                                                      @Param("jumlahBarangAwal") String jumlahBarangAwal,
                                                     @Param("jumlahBarangAkhir") String jumlahBarangAkhir,
                                                      @Param("hargaSatuanAwal") String hargaSatuanAwal,
                                                     @Param("hargaSatuanAkhir") String hargaSatuanAkhir);
}
