package com.kondangan.domain.model;

import com.kondangan.util.Constanta;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserFormModel implements Serializable {
    private BigDecimal id;
    @NotBlank(message = Constanta.MSG_FORM_NOTNULL)
    private String username;
    private String nama;
    @NotBlank(message = Constanta.MSG_FORM_NOTNULL)
    private String password;
    private String level;
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date cretionDate;
    private String lastUpdateBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
}
