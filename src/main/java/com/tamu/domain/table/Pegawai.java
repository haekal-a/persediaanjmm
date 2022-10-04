package com.tamu.domain.table;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name="pegawai", schema = "public")
public class Pegawai implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private BigDecimal id;
    private String nama;
    private String nip;
    private String nip9;
    private String jabatan;
    private String unit;
}
