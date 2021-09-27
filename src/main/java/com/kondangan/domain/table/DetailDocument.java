package com.kondangan.domain.table;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table
public class DetailDocument {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private BigDecimal idDetailDocument;
  private String idDeliverable;
  private Integer period;
  private String idMonitoring;
  private Integer version;
  private String versionName;
  @Temporal(TemporalType.DATE)
  private Date tglSubmission;
  private String suratDeloitte;
  @Temporal(TemporalType.DATE)
  private Date tglSurat;
  private String ndPenerusanPpk;
  @Temporal(TemporalType.DATE)
  private Date tglNd;
  private Integer flagPsiap;
  private String ndPsiap;
  @Temporal(TemporalType.DATE)
  private Date tglNdPsiap;
  private String baSteerco;
  @Temporal(TemporalType.DATE)
  private Date tglBaSteerco;
  private String noApproveSteerco;
  @Temporal(TemporalType.DATE)
  private Date tglApprove;
  private String sPemberitahuanPpk;
  @Temporal(TemporalType.DATE)
  private Date tglS;
  private String baKemajuan;
  @Temporal(TemporalType.DATE)
  private Date tglBaKemajuan;
  private String bast;
  @Temporal(TemporalType.DATE)
  private Date tglBast;
  private String baPembayaran;
  @Temporal(TemporalType.DATE)
  private Date tglBaPembayaran;
  private String tagihan;
  @Temporal(TemporalType.DATE)
  private Date tglTagihan;
  private String ndPermohonanBayar;
  @Temporal(TemporalType.DATE)
  private Date tglNdPermohonanBayar;
  private String spp;
  @Temporal(TemporalType.DATE)
  private Date tglSpp;
  private String spm;
  @Temporal(TemporalType.DATE)
  private Date tglSpm;
  private String sp2D;
  @Temporal(TemporalType.DATE)
  private Date tglSp2D;
  private String statusDeliverable;
  private String statusPembayaran;
  private String createdBy;
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;
  private String updatedBy;
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedDate;


  public BigDecimal getIdDetailDocument() {
    return idDetailDocument;
  }

  public void setIdDetailDocument(BigDecimal idDetailDocument) {
    this.idDetailDocument = idDetailDocument;
  }


  public String getIdDeliverable() {
    return idDeliverable;
  }

  public void setIdDeliverable(String idDeliverable) {
    this.idDeliverable = idDeliverable;
  }


  public Integer getPeriod() {
    return period;
  }

  public void setPeriod(Integer period) {
    this.period = period;
  }


  public String getIdMonitoring() {
    return idMonitoring;
  }

  public void setIdMonitoring(String idMonitoring) {
    this.idMonitoring = idMonitoring;
  }


  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }


  public String getVersionName() {
    return versionName;
  }

  public void setVersionName(String versionName) {
    this.versionName = versionName;
  }


  public Date getTglSubmission() {
    return tglSubmission;
  }

  public void setTglSubmission(Date tglSubmission) {
    this.tglSubmission = tglSubmission;
  }


  public String getSuratDeloitte() {
    return suratDeloitte;
  }

  public void setSuratDeloitte(String suratDeloitte) {
    this.suratDeloitte = suratDeloitte;
  }


  public Date getTglSurat() {
    return tglSurat;
  }

  public void setTglSurat(Date tglSurat) {
    this.tglSurat = tglSurat;
  }


  public String getNdPenerusanPpk() {
    return ndPenerusanPpk;
  }

  public void setNdPenerusanPpk(String ndPenerusanPpk) {
    this.ndPenerusanPpk = ndPenerusanPpk;
  }


  public Date getTglNd() {
    return tglNd;
  }

  public void setTglNd(Date tglNd) {
    this.tglNd = tglNd;
  }


  public Integer getFlagPsiap() {
    return flagPsiap;
  }

  public void setFlagPsiap(Integer flagPsiap) {
    this.flagPsiap = flagPsiap;
  }


  public String getNdPsiap() {
    return ndPsiap;
  }

  public void setNdPsiap(String ndPsiap) {
    this.ndPsiap = ndPsiap;
  }


  public Date getTglNdPsiap() {
    return tglNdPsiap;
  }

  public void setTglNdPsiap(Date tglNdPsiap) {
    this.tglNdPsiap = tglNdPsiap;
  }


  public String getBaSteerco() {
    return baSteerco;
  }

  public void setBaSteerco(String baSteerco) {
    this.baSteerco = baSteerco;
  }


  public Date getTglBaSteerco() {
    return tglBaSteerco;
  }

  public void setTglBaSteerco(Date tglBaSteerco) {
    this.tglBaSteerco = tglBaSteerco;
  }


  public String getNoApproveSteerco() {
    return noApproveSteerco;
  }

  public void setNoApproveSteerco(String noApproveSteerco) {
    this.noApproveSteerco = noApproveSteerco;
  }


  public Date getTglApprove() {
    return tglApprove;
  }

  public void setTglApprove(Date tglApprove) {
    this.tglApprove = tglApprove;
  }


  public String getSPemberitahuanPpk() {
    return sPemberitahuanPpk;
  }

  public void setSPemberitahuanPpk(String sPemberitahuanPpk) {
    this.sPemberitahuanPpk = sPemberitahuanPpk;
  }


  public Date getTglS() {
    return tglS;
  }

  public void setTglS(Date tglS) {
    this.tglS = tglS;
  }


  public String getBaKemajuan() {
    return baKemajuan;
  }

  public void setBaKemajuan(String baKemajuan) {
    this.baKemajuan = baKemajuan;
  }


  public Date getTglBaKemajuan() {
    return tglBaKemajuan;
  }

  public void setTglBaKemajuan(Date tglBaKemajuan) {
    this.tglBaKemajuan = tglBaKemajuan;
  }


  public String getBast() {
    return bast;
  }

  public void setBast(String bast) {
    this.bast = bast;
  }


  public Date getTglBast() {
    return tglBast;
  }

  public void setTglBast(Date tglBast) {
    this.tglBast = tglBast;
  }


  public String getBaPembayaran() {
    return baPembayaran;
  }

  public void setBaPembayaran(String baPembayaran) {
    this.baPembayaran = baPembayaran;
  }


  public Date getTglBaPembayaran() {
    return tglBaPembayaran;
  }

  public void setTglBaPembayaran(Date tglBaPembayaran) {
    this.tglBaPembayaran = tglBaPembayaran;
  }


  public String getTagihan() {
    return tagihan;
  }

  public void setTagihan(String tagihan) {
    this.tagihan = tagihan;
  }


  public Date getTglTagihan() {
    return tglTagihan;
  }

  public void setTglTagihan(Date tglTagihan) {
    this.tglTagihan = tglTagihan;
  }


  public String getNdPermohonanBayar() {
    return ndPermohonanBayar;
  }

  public void setNdPermohonanBayar(String ndPermohonanBayar) {
    this.ndPermohonanBayar = ndPermohonanBayar;
  }


  public Date getTglNdPermohonanBayar() {
    return tglNdPermohonanBayar;
  }

  public void setTglNdPermohonanBayar(Date tglNdPermohonanBayar) {
    this.tglNdPermohonanBayar = tglNdPermohonanBayar;
  }


  public String getSpp() {
    return spp;
  }

  public void setSpp(String spp) {
    this.spp = spp;
  }


  public Date getTglSpp() {
    return tglSpp;
  }

  public void setTglSpp(Date tglSpp) {
    this.tglSpp = tglSpp;
  }


  public String getSpm() {
    return spm;
  }

  public void setSpm(String spm) {
    this.spm = spm;
  }


  public Date getTglSpm() {
    return tglSpm;
  }

  public void setTglSpm(Date tglSpm) {
    this.tglSpm = tglSpm;
  }


  public String getSp2D() {
    return sp2D;
  }

  public void setSp2D(String sp2D) {
    this.sp2D = sp2D;
  }


  public Date getTglSp2D() {
    return tglSp2D;
  }

  public void setTglSp2D(Date tglSp2D) {
    this.tglSp2D = tglSp2D;
  }


  public String getStatusDeliverable() {
    return statusDeliverable;
  }

  public void setStatusDeliverable(String statusDeliverable) {
    this.statusDeliverable = statusDeliverable;
  }


  public String getStatusPembayaran() {
    return statusPembayaran;
  }

  public void setStatusPembayaran(String statusPembayaran) {
    this.statusPembayaran = statusPembayaran;
  }


  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }


  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }


  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }


  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

}
