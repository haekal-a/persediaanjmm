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
  private int period;
  private String latestVersion;
  private int position;
  private int softcopyStatus;
  private int hardcopyStatus;
  private int hardcopyIn;
  private int hardcopyOut;
  private int hardcopyLeft;
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


  public int getPeriod() {
    return period;
  }

  public void setPeriod(int period) {
    this.period = period;
  }


  public String getLatestVersion() {
    return latestVersion;
  }

  public void setLatestVersion(String latestVersion) {
    this.latestVersion = latestVersion;
  }


  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
  }


  public int getSoftcopyStatus() {
    return softcopyStatus;
  }

  public void setSoftcopyStatus(int softcopyStatus) {
    this.softcopyStatus = softcopyStatus;
  }


  public int getHardcopyStatus() {
    return hardcopyStatus;
  }

  public void setHardcopyStatus(int hardcopyStatus) {
    this.hardcopyStatus = hardcopyStatus;
  }


  public int getHardcopyIn() {
    return hardcopyIn;
  }

  public void setHardcopyIn(int hardcopyIn) {
    this.hardcopyIn = hardcopyIn;
  }


  public int getHardcopyOut() {
    return hardcopyOut;
  }

  public void setHardcopyOut(int hardcopyOut) {
    this.hardcopyOut = hardcopyOut;
  }


  public int getHardcopyLeft() {
    return hardcopyLeft;
  }

  public void setHardcopyLeft(int hardcopyLeft) {
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
