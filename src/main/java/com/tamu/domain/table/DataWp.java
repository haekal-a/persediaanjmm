package com.tamu.domain.table;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="datawp", schema = "public")
public class DataWp {

    @Id
    @GeneratedValue(generator = "uuidGen")
    @GenericGenerator(name = "uuidGen", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Temporal(TemporalType.DATE)
    private Date tanggalLahir;
    @Column(name="npwp_15")
    private String npwp15;
    private String namaWp;
    private String alamat;
    private String kota;
    private String kodePos;
    private String nomorTelepon;
    private String nomorFax;
    private String email;
    private String nomorIdentitas;
    private String jenisWp;
    private String kodeKlu;
    private String namaKlu;
    private String kelurahan;
    private String kecamatan;
    private String propinsi;
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    private String lastUpdatedBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
}
