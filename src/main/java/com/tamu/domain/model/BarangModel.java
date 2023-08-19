package com.tamu.domain.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BarangModel implements Serializable {
    private String idPersediaan;
    private String namaBarang;
    private String jenisBarang;
    private String merk;
    private String keterangan;

}
