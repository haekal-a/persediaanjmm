package com.tamu.domain.table;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
public class Transaksi implements Serializable {
    @GeneratedValue(generator = "uuidGen")
    @GenericGenerator(name = "uuidGen", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    @Column(name = "id_transaksi", nullable = false, length = 36)
    private String idTransaksi;
    @Basic
    @Column(name = "id_persediaan", nullable = false, length = 36)
    private String idPersediaan;
    @Basic
    @Column(name = "kode_transaksi", nullable = false, length = 12)
    private String kodeTransaksi;
    @Basic
    @Column(name = "title", nullable = false, length = 150)
    private String title;
    @Basic
    @Column(name = "jenis_transaksi", nullable = false)
    private Integer jenisTransaksi;
    @Basic
    @Column(name = "tanggal_transaksi", nullable = false)
    private Date tanggalTransaksi;
    @Basic
    @Column(name = "keterangan", nullable = true, length = 255)
    private String keterangan;
    @Basic
    @Column(name = "harga_satuan", nullable = false)
    private Integer hargaSatuan;
    @Basic
    @Column(name = "jumlah_barang", nullable = false)
    private Integer jumlahBarang;
    @Basic
    @Column(name = "sisa_barang", nullable = false)
    private Integer sisaBarang;
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false)
    private Date creationDate;
    @Basic
    @Column(name = "created_by", nullable = false, length = 20)
    private String createdBy;
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date", nullable = true)
    private Date updatedDate;
    @Basic
    @Column(name = "updated_by", nullable = true, length = 20)
    private String updatedBy;

    /*public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getIdPersediaan() {
        return idPersediaan;
    }

    public void setIdPersediaan(String idPersediaan) {
        this.idPersediaan = idPersediaan;
    }

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getJenisTransaksi() {
        return jenisTransaksi;
    }

    public void setJenisTransaksi(Integer jenisTransaksi) {
        this.jenisTransaksi = jenisTransaksi;
    }

    public Date getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(Date tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Integer getHargaSatuan() {
        return hargaSatuan;
    }

    public void setHargaSatuan(Integer hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }

    public Integer getJumlahBarang() {
        return jumlahBarang;
    }

    public void setJumlahBarang(Integer jumlahBarang) {
        this.jumlahBarang = jumlahBarang;
    }

    public Integer getSisaBarang() {
        return sisaBarang;
    }

    public void setSisaBarang(Integer sisaBarang) {
        this.sisaBarang = sisaBarang;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaksi transaksi = (Transaksi) o;
        return Objects.equals(idTransaksi, transaksi.idTransaksi) && Objects.equals(idPersediaan, transaksi.idPersediaan) && Objects.equals(kodeTransaksi, transaksi.kodeTransaksi) && Objects.equals(title, transaksi.title) && Objects.equals(jenisTransaksi, transaksi.jenisTransaksi) && Objects.equals(tanggalTransaksi, transaksi.tanggalTransaksi) && Objects.equals(keterangan, transaksi.keterangan) && Objects.equals(hargaSatuan, transaksi.hargaSatuan) && Objects.equals(jumlahBarang, transaksi.jumlahBarang) && Objects.equals(sisaBarang, transaksi.sisaBarang) && Objects.equals(creationDate, transaksi.creationDate) && Objects.equals(createdBy, transaksi.createdBy) && Objects.equals(updatedDate, transaksi.updatedDate) && Objects.equals(updatedBy, transaksi.updatedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransaksi, idPersediaan, kodeTransaksi, title, jenisTransaksi, tanggalTransaksi, keterangan, hargaSatuan, jumlahBarang, sisaBarang, creationDate, createdBy, updatedDate, updatedBy);
    }*/
}
