package com.kondangan.domain.table;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table
public class Master implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private BigDecimal idMaster;
  private String idDeliverable;
  private String sectionNo;
  private String function;
  private String taskNo;
  private String task;
  private String deliverableName;
  private String scheduleInTor;
  private String createdBy;
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;
  private String updatedBy;
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedDate;


  public BigDecimal getIdMaster() {
    return idMaster;
  }

  public void setIdMaster(BigDecimal idMaster) {
    this.idMaster = idMaster;
  }


  public String getIdDeliverable() {
    return idDeliverable;
  }

  public void setIdDeliverable(String idDeliverable) {
    this.idDeliverable = idDeliverable;
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
