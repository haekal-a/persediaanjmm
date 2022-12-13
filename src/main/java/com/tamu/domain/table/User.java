package com.tamu.domain.table;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name="user", schema = "public")
public class User implements Serializable {

  @Id
  @GeneratedValue(generator = "uuidGen")
  @GenericGenerator(name = "uuidGen", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;
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
