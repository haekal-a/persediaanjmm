package com.kondangan.domain.model;

import com.kondangan.util.Constanta;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
public class MonitoringFormModel implements Serializable {
    private String idMonitoring;
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
    @NotBlank(message = Constanta.MSG_FORM_NOTNULL)
    private String deliverableCode;
    private String monthSubmission;
    @NotBlank(message = Constanta.MSG_FORM_NOTNULL)
    private String submissionStatus;
    @NotNull(message = Constanta.MSG_FORM_NOTNULL)
//    @Size(min = 8, message = Constanta.MSG_FORM_MINLENGTH)
    @Digits(integer = 8, fraction = 0, message = Constanta.MSG_FORM_DIGITS)
    private Integer period;
    private String latestVersion;
    private Integer position;
    @NotNull(message = Constanta.MSG_FORM_NOTNULL)
    @Digits(integer = 1, fraction = 0, message = Constanta.MSG_FORM_DIGITS)
    private Integer softcopyStatus;
    @NotNull(message = Constanta.MSG_FORM_NOTNULL)
    @Digits(integer = 1, fraction = 0, message = Constanta.MSG_FORM_DIGITS)
    private Integer hardcopyStatus;
    private Integer hardcopyIn;
    private Integer hardcopyOut;
    private Integer hardcopyLeft;
    private String paymentStatus;
    @NotBlank(message = Constanta.MSG_FORM_NOTNULL)
    private String keterangan;
}
