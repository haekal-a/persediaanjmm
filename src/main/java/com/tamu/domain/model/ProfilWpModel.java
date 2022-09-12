package com.tamu.domain.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProfilWpModel implements Serializable {
    private String id;
    private String alamat;
    private String kelurahan;
    private String kecamatan;
    private String kota;
    private String propinsi;
    private String kodePos;
    private String nomorTelepon;
    private String nomorFax;
    private String email;
}
