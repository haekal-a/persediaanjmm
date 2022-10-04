package com.tamu.domain.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class KunjunganModel implements Serializable {
    private String idWp;
    private String idPegawai;
    private String nip;
    private String jabatan;
    private String unit;
    private String keperluan;
}
