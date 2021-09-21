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
    @NotBlank(message = Constanta.MSG_FORM_NOTNULL)
    private String monthSubmission;
    @NotBlank(message = Constanta.MSG_FORM_NOTNULL)
    private String submissionStatus;
    @NotNull(message = Constanta.MSG_FORM_NOTNULL)
//    @Size(min = 9, message = Message.MSG_FORM_MINLENGTH)
    @Digits(integer = 1, fraction = 0, message = Constanta.MSG_FORM_DIGITS)
    private int period;
    @NotBlank(message = Constanta.MSG_FORM_NOTNULL)
    private String latestVersion;
    @NotNull(message = Constanta.MSG_FORM_NOTNULL)
    @Digits(integer = 1, fraction = 0, message = Constanta.MSG_FORM_DIGITS)
    private int position;
    @NotNull(message = Constanta.MSG_FORM_NOTNULL)
    @Digits(integer = 1, fraction = 0, message = Constanta.MSG_FORM_DIGITS)
    private int softcopyStatus;
    @NotNull(message = Constanta.MSG_FORM_NOTNULL)
    @Digits(integer = 1, fraction = 0, message = Constanta.MSG_FORM_DIGITS)
    private int hardcopyStatus;
    private int hardcopyIn;
    private int hardcopyOut;
    private int hardcopyLeft;
    @NotBlank(message = Constanta.MSG_FORM_NOTNULL)
    private String paymentStatus;
    @NotBlank(message = Constanta.MSG_FORM_NOTNULL)
    private String keterangan;
}
