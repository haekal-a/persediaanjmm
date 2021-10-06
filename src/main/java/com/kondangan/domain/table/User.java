package com.kondangan.domain.table;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private BigDecimal id;
  private String username;
  private String nama;
  private String password;
  private String level;
  private String createdBy;
  private String lastUpdateBy;
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastUpdateDate;
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;
}
