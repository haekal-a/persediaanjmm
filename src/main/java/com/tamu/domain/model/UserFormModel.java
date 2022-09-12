package com.tamu.domain.model;

import com.tamu.util.Constanta;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
public class UserFormModel implements Serializable {
    private String id;
    @NotBlank(message = Constanta.MSG_FORM_NOTNULL)
    private String username;
    @NotBlank(message = Constanta.MSG_FORM_NOTNULL)
    private String nama;
    @NotBlank(message = Constanta.MSG_FORM_NOTNULL)
    private String password;
    @NotBlank(message = Constanta.MSG_FORM_NOTNULL)
    private String level;
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    private String lastUpdateBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
}
