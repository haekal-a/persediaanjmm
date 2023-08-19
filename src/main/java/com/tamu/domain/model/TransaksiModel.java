package com.tamu.domain.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TransaksiModel implements Serializable {
    private String idTransaksi;
    private String idPersediaan;
    private String ddlJenisTransaksi;
    private String tanggalTransaksi;
    private String kodeTransaksi;
    private String title;
    private String keterangan;
    private String jumlahBarang;
    private String hargaSatuan;

}
