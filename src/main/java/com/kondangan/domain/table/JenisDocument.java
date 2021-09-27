package com.kondangan.domain.table;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
public class JenisDocument {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private BigDecimal idJenisDocument;
  private String nmDocument;
  private String keterangan;


  public BigDecimal getIdJenisDocument() {
    return idJenisDocument;
  }

  public void setIdJenisDocument(BigDecimal idJenisDocument) {
    this.idJenisDocument = idJenisDocument;
  }


  public String getNmDocument() {
    return nmDocument;
  }

  public void setNmDocument(String nmDocument) {
    this.nmDocument = nmDocument;
  }


  public String getKeterangan() {
    return keterangan;
  }

  public void setKeterangan(String keterangan) {
    this.keterangan = keterangan;
  }

}
