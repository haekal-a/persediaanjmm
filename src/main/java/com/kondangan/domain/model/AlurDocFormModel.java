package com.kondangan.domain.model;

import lombok.Data;

import java.util.Date;

@Data
public class AlurDocFormModel {
    private String idDetailDocument;
    private String idDeliverable;
    private Integer period;
    private String idMonitoring;
    private Integer version;
    private String versionName;
    private Date tglSubmission;
    private String suratDeloitte;
    private Date tglSurat;
    private String ndPenerusanPpk;
    private Date tglNd;
    private Integer flagPsiap;
    private String ndPsiap;
    private Date tglNdPsiap;
    private String baSteerco;
    private Date tglBaSteerco;
    private String noApproveSteerco;
    private Date tglApprove;
    private String sPemberitahuanPpk;
    private Date tglS;
    private String baKemajuan;
    private Date tglBaKemajuan;
    private String bast;
    private Date tglBast;
    private String baPembayaran;
    private Date tglBaPembayaran;
    private String tagihan;
    private Date tglTagihan;
    private String ndPermohonanBayar;
    private Date tglNdPermohonanBayar;
    private String spp;
    private Date tglSpp;
    private String spm;
    private Date tglSpm;
    private String sp2D;
    private Date tglSp2D;
    private String statusDeliverable;
    private String statusPembayaran;
}
