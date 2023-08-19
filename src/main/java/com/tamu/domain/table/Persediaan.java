package com.tamu.domain.table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
public class Persediaan implements Serializable {
    @GeneratedValue(generator = "uuidGen")
    @GenericGenerator(name = "uuidGen", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    @Column(name = "id_persediaan", nullable = false, length = 36)
    private String idPersediaan;
    @Basic
    @Column(name = "kode_barang", nullable = false, length = 4)
    private String kodeBarang;
    @Basic
    @Column(name = "nama_barang", nullable = false, length = 150)
    private String namaBarang;
    @Basic
    @Column(name = "jenis_barang", nullable = false, length = 50)
    private String jenisBarang;
    @Basic
    @Column(name = "merk", nullable = false, length = 50)
    private String merk;
    @Basic
    @Column(name = "keterangan", nullable = true, length = 255)
    private String keterangan;
    @Basic
    @Column(name = "jumlah_barang", nullable = false)
    private Integer jumlahBarang;
    @Basic
    @Column(name = "harga_satuan", nullable = false)
    private Integer hargaSatuan;
    @Basic
    @Column(name = "harga_perolehan", nullable = false)
    private Integer hargaPerolehan;
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

    public String getIdPersediaan() {
        return idPersediaan;
    }

    public void setIdPersediaan(String idPersediaan) {
        this.idPersediaan = idPersediaan;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getJenisBarang() {
        return jenisBarang;
    }

    public void setJenisBarang(String jenisBarang) {
        this.jenisBarang = jenisBarang;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Integer getJumlahBarang() {
        return jumlahBarang;
    }

    public void setJumlahBarang(Integer jumlahBarang) {
        this.jumlahBarang = jumlahBarang;
    }

    public Integer getHargaSatuan() {
        return hargaSatuan;
    }

    public void setHargaSatuan(Integer hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }

    public Integer getHargaPerolehan() {
        return hargaPerolehan;
    }

    public void setHargaPerolehan(Integer hargaPerolehan) {
        this.hargaPerolehan = hargaPerolehan;
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
        Persediaan that = (Persediaan) o;
        return Objects.equals(idPersediaan, that.idPersediaan) && Objects.equals(kodeBarang, that.kodeBarang) && Objects.equals(namaBarang, that.namaBarang) && Objects.equals(jenisBarang, that.jenisBarang) && Objects.equals(merk, that.merk) && Objects.equals(keterangan, that.keterangan) && Objects.equals(jumlahBarang, that.jumlahBarang) && Objects.equals(hargaSatuan, that.hargaSatuan) && Objects.equals(hargaPerolehan, that.hargaPerolehan) && Objects.equals(creationDate, that.creationDate) && Objects.equals(createdBy, that.createdBy) && Objects.equals(updatedDate, that.updatedDate) && Objects.equals(updatedBy, that.updatedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersediaan, kodeBarang, namaBarang, jenisBarang, merk, keterangan, jumlahBarang, hargaSatuan, hargaPerolehan, creationDate, createdBy, updatedDate, updatedBy);
    }
}
