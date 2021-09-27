package com.kondangan.domain.table;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table
public class Monitoring implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private BigDecimal idMonitoring;
  private String sectionNo;
  private String function;
  private String taskNo;
  private String task;
  private String deliverableName;
  private String scheduleInTor;
  private String deliverableCode;
  private String monthSubmission;
  private String submissionStatus;
  private Integer period;
  private String latestVersion;
  private Integer position;
  private Integer softcopyStatus;
  private Integer hardcopyStatus;
  private Integer hardcopyIn;
  private Integer hardcopyOut;
  private Integer hardcopyLeft;
  private String paymentStatus;
  private String keterangan;
  private String createdBy;
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;
  private String updatedBy;
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedDate;


  public BigDecimal getIdMonitoring() {
    return idMonitoring;
  }

  public void setIdMonitoring(BigDecimal idMonitoring) {
    this.idMonitoring = idMonitoring;
  }


  public String getSectionNo() {
    return sectionNo;
  }

  public void setSectionNo(String sectionNo) {
    this.sectionNo = sectionNo;
  }


  public String getFunction() {
    return function;
  }

  public void setFunction(String function) {
    this.function = function;
  }


  public String getTaskNo() {
    return taskNo;
  }

  public void setTaskNo(String taskNo) {
    this.taskNo = taskNo;
  }


  public String getTask() {
    return task;
  }

  public void setTask(String task) {
    this.task = task;
  }


  public String getDeliverableName() {
    return deliverableName;
  }

  public void setDeliverableName(String deliverableName) {
    this.deliverableName = deliverableName;
  }


  public String getScheduleInTor() {
    return scheduleInTor;
  }

  public void setScheduleInTor(String scheduleInTor) {
    this.scheduleInTor = scheduleInTor;
  }


  public String getDeliverableCode() {
    return deliverableCode;
  }

  public void setDeliverableCode(String deliverableCode) {
    this.deliverableCode = deliverableCode;
  }


  public String getMonthSubmission() {
    return monthSubmission;
  }

  public void setMonthSubmission(String monthSubmission) {
    this.monthSubmission = monthSubmission;
  }


  public String getSubmissionStatus() {
    return submissionStatus;
  }

  public void setSubmissionStatus(String submissionStatus) {
    this.submissionStatus = submissionStatus;
  }


  public Integer getPeriod() {
    return period;
  }

  public void setPeriod(Integer period) {
    this.period = period;
  }


  public String getLatestVersion() {
    return latestVersion;
  }

  public void setLatestVersion(String latestVersion) {
    this.latestVersion = latestVersion;
  }


  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }


  public Integer getSoftcopyStatus() {
    return softcopyStatus;
  }

  public void setSoftcopyStatus(Integer softcopyStatus) {
    this.softcopyStatus = softcopyStatus;
  }


  public Integer getHardcopyStatus() {
    return hardcopyStatus;
  }

  public void setHardcopyStatus(Integer hardcopyStatus) {
    this.hardcopyStatus = hardcopyStatus;
  }


  public Integer getHardcopyIn() {
    return hardcopyIn;
  }

  public void setHardcopyIn(Integer hardcopyIn) {
    this.hardcopyIn = hardcopyIn;
  }


  public Integer getHardcopyOut() {
    return hardcopyOut;
  }

  public void setHardcopyOut(Integer hardcopyOut) {
    this.hardcopyOut = hardcopyOut;
  }


  public Integer getHardcopyLeft() {
    return hardcopyLeft;
  }

  public void setHardcopyLeft(Integer hardcopyLeft) {
    this.hardcopyLeft = hardcopyLeft;
  }


  public String getPaymentStatus() {
    return paymentStatus;
  }

  public void setPaymentStatus(String paymentStatus) {
    this.paymentStatus = paymentStatus;
  }


  public String getKeterangan() {
    return keterangan;
  }

  public void setKeterangan(String keterangan) {
    this.keterangan = keterangan;
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
