package com.tamu.domain.table;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name="kunjungan", schema = "public")
public class Kunjungan implements Serializable {

    @Id
    @SequenceGenerator(sequenceName="seq_kunjungan", name="SeqKunjungan", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SeqKunjungan")
    private BigDecimal id;
    private BigDecimal idMfwp;
    private BigDecimal idPegawai;
    private String keperluan;
    private String fgStatus;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    private String lastUpdatedBy;
}
