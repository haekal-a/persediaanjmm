package com.kondangan.domain.model;

import com.kondangan.util.Constanta;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class MasterFormModel implements Serializable {
    private String idMaster;
    @NotBlank(message = Constanta.MSG_FORM_NOTNULL)
    private String idDeliverable;
    @NotBlank(message = Constanta.MSG_FORM_NOTNULL)
    private String sectionNo;
    @NotBlank(message = Constanta.MSG_FORM_NOTNULL)
    private String function;
    @NotBlank(message = Constanta.MSG_FORM_NOTNULL)
    private String taskNo;
    @NotBlank(message = Constanta.MSG_FORM_NOTNULL)
    private String task;
    @NotBlank(message = Constanta.MSG_FORM_NOTNULL)
    private String deliverableName;
    @NotBlank(message = Constanta.MSG_FORM_NOTNULL)
    private String scheduleInTor;
}
