package com.tamu.service.common;

import com.tamu.repository.repomodel.MonitoringRepoModel;

import java.util.Collections;
import java.util.List;

public final class RepoQuery {
    public static final String GET_ALL_KUNJUNGAN =
            " select " +
                    " idKunjungan, " +
                    " namaWp, " +
                    " npwp, " +
                    " namaPegawai, " +
                    " nip, " +
                    " jabatan, " +
                    " unit, " +
                    " keperluan, " +
                    " status, " +
                    " keterangan, " +
                    " DATE_FORMAT(creation_date, '%d-%m-%Y') as tanggal " +
                    "from " +
                    " ( " +
                    " select " +
                    "  k.id idKunjungan, " +
                    "  m.nama_wp namaWp, " +
                    "  m.npwp_15 npwp, " +
                    "  p.nama namaPegawai, " +
                    "  p.nip, " +
                    "  p.jabatan, " +
                    "  p.unit, " +
                    "  k.keperluan, " +
                    "  case " +
                    "   when k.fg_status = '1' then 'Menunggu' " +
                    "   when k.fg_status = '2' then 'Selesai' " +
                    "   when k.fg_status = '3' then 'Batal' " +
                    "   else 'Tidak terdefinisi' " +
                    "  end status, " +
                    "  k.keterangan, " +
                    "  k.creation_date " +
                    " from " +
                    "  kunjungan k " +
                    " join mfwp m on " +
                    "  k.npwp = m.npwp_15  " +
                    " join pegawai p on " +
                    "  k.id_pegawai = p.id " +
                    "union " +
                    " select " +
                    "  k.id idKunjungan, " +
                    "  m.nama_wp namaWp, " +
                    "  m.npwp_15 npwp, " +
                    "  p.nama namaPegawai, " +
                    "  p.nip, " +
                    "  p.jabatan, " +
                    "  p.unit, " +
                    "  k.keperluan, " +
                    "  case " +
                    "   when k.fg_status = '1' then 'Menunggu' " +
                    "   when k.fg_status = '2' then 'Selesai' " +
                    "   when k.fg_status = '3' then 'Batal' " +
                    "   else 'Tidak terdefinisi' " +
                    "  end status, " +
                    "  k.keterangan, " +
                    "  k.creation_date " +
                    " from " +
                    "  kunjungan k " +
                    " join datawp m on " +
                    "  k.id_datawp = m.id " +
                    " join pegawai p on " +
                    "  k.id_pegawai = p.id ) a " +
                    "where " +
                    " (a.namaWp like :namaWp " +
                    "  or :namaWp is null) " +
                    " and (a.npwp = :npwp " +
                    "  or :npwp is null) " +
                    "  and (a.nip = :nip or :nip is null) " +
                    "  and (a.creation_date >= STR_TO_DATE(:tglAwal,'%d-%m-%Y') or :tglAwal is null) " +
                    "  and (a.creation_date <= STR_TO_DATE(:tglAkhir,'%d-%m-%Y') or :tglAkhir is null) " +
                    "  and (a.status = :status or :status is null) " +
                    "order by " +
                    " a.creation_date desc ";

    public static final String GET_LAST_KODE_BARANG = "SELECT RIGHT( `kode_barang`, 3) FROM `persediaan` WHERE LEFT( `kode_barang`, 1) = :jenisBarang ORDER BY `creation_date` DESC LIMIT 1 ";
    public static final String GET_ALL_JENIS_BARANG = "SELECT DISTINCT(`jenis_barang`) FROM `persediaan`";
    public static final String GET_ALL_MERK = "SELECT DISTINCT(`merk`) FROM `persediaan`";
    
    public static final String GET_MONITORING_TRANSAKSI = " SELECT " +
            "    a.id_transaksi idTransaksi, " +
            "    a.kode_transaksi kodeTransaksi, " +
            "    a.jenis_transaksi jenisTransaksi, " +
            "    a.tanggal_transaksi tanggalTransaksi, " +
            "    a.keterangan keteranganTrx, " +
            "    a.title, " +
            "    a.harga_satuan hargaSatuanTrx, " +
            "    a.jumlah_barang jumlahBarangTrx, " +
            "    a.sisa_barang sisaBarang, " +
            "    b.id_persediaan idPersediaan, " +
            "    b.kode_barang kodeBarang, " +
            "    b.nama_barang namaBarang, " +
            "    b.jenis_barang jenisBarang, " +
            "    b.merk, " +
            "    b.keterangan, " +
            "    b.harga_satuan hargaSatuan, " +
            "    b.harga_perolehan hargaPerolehan, " +
            "    b.jumlah_barang jumlahBarang " +
            "FROM " +
            "    transaksi a " +
            "JOIN persediaan b ON " +
            "    a.id_persediaan = b.id_persediaan " +
            "WHERE " +
            "    (a.jenis_transaksi = :jenisTransaksi OR :jenisTransaksi is null) " +
            "    AND (find_in_set(b.jenis_barang, :jenisBarang) OR :jenisBarang is null) " +
            "    AND (find_in_set(b.nama_barang, :namaBarang) OR :namaBarang is null) " +
            "    and (a.tanggal_transaksi >= STR_TO_DATE(:tglAwal,'%d-%m-%Y') or :tglAwal is null) " +
            "    and (a.tanggal_transaksi <= STR_TO_DATE(:tglAkhir,'%d-%m-%Y') or :tglAkhir is null) " +
            "    ORDER by a.creation_date DESC ";
    
    public static final String GET_MONITORING_PERSEDIAAN = "SELECT  " +
            "    b.id_persediaan idPersediaan,  " +
            "    b.kode_barang kodeBarang,  " +
            "    b.nama_barang namaBarang,  " +
            "    b.jenis_barang jenisBarang,  " +
            "    b.merk,  " +
            "    b.keterangan,  " +
            "    b.harga_satuan hargaSatuan,  " +
            "    b.harga_perolehan hargaPerolehan,  " +
            "    b.jumlah_barang jumlahBarang  " +
            "FROM  " +
            "    persediaan b   " +
            "WHERE  " +
            "    (find_in_set(b.jenis_barang, :jenisBarang) OR :jenisBarang is null)  " +
            "    AND (find_in_set(b.nama_barang, :namaBarang) OR :namaBarang is null)  " +
            "    AND (find_in_set(b.merk, :merk) OR :merk is null)  " +
            "    and (b.jumlah_barang >= :jumlahBarangAwal or :jumlahBarangAwal is null)  " +
            "    and (b.jumlah_barang <= :jumlahBarangAkhir or :jumlahBarangAkhir is null)  " +
            "    and (b.harga_satuan >= :hargaSatuanAwal or :hargaSatuanAwal is null)  " +
            "    and (b.harga_satuan <= :hargaSatuanAkhir or :hargaSatuanAkhir is null)  " +
            "    ORDER by b.creation_date DESC ";
}
