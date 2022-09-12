package com.tamu.domain.table;

import lombok.Data;
import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name="mfwp", schema = "public")
public class Mfwp implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private BigDecimal id;
  @Temporal(TemporalType.DATE)
  private Date tanggalDaftar;
  @Temporal(TemporalType.DATE)
  private Date tanggalPindah;
  @Temporal(TemporalType.DATE)
  private Date tanggalLahir;
  private String npwp;
  private String kdKpp;
  private String kdCabang;
  @Column(name="npwp_15")
  private String npwp15;
  private String namaWp;
  private String alamat;
  private String kota;
  private String kodePos;
  private String nomorTelepon;
  private String nomorFax;
  private String email;
  private String nomorIdentitas;
  private String statusWp;
  private String jenisWp;
  private String kodeKlu;
  private String namaKlu;
  @Temporal(TemporalType.DATE)
  private Date tanggalPkp;
  private String kelurahan;
  private String kecamatan;
  private String propinsi;
  private String bentukHukum;
  private String mataUang;
  private String noSkt;
  private String noPkp;
  private String noPkpCabut;
  @Temporal(TemporalType.DATE)
  private Date tglPkpCabut;
  private String metodePerhitungan;
  private String nipAr;
  private String namaAr;
  private String nipJs;
  private String namaJs;
  private String nipEks;
  private String namaEks;
  private String jnsBadanHukum;
  private String statusModal;
  private String kategori;
  private String idBlBukuAwal;
  private String idBlBukuAkhir;
}
