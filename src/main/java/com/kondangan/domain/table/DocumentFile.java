package com.kondangan.domain.table;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
public class DocumentFile {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private BigDecimal idDocument;
  private String idDetailDocument;
  private String idJenisDocument;
  private String documentName;
  private String path;


  public BigDecimal getIdDocument() {
    return idDocument;
  }

  public void setIdDocument(BigDecimal idDocument) {
    this.idDocument = idDocument;
  }


  public String getIdDetailDocument() {
    return idDetailDocument;
  }

  public void setIdDetailDocument(String idDetailDocument) {
    this.idDetailDocument = idDetailDocument;
  }


  public String getIdJenisDocument() {
    return idJenisDocument;
  }

  public void setIdJenisDocument(String idJenisDocument) {
    this.idJenisDocument = idJenisDocument;
  }


  public String getDocumentName() {
    return documentName;
  }

  public void setDocumentName(String documentName) {
    this.documentName = documentName;
  }


  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

}
