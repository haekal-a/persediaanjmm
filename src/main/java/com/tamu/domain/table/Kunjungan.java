package com.tamu.domain.table;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name="kunjungan", schema = "public")
public class Kunjungan implements Serializable {

    @Id
//    @SequenceGenerator(sequenceName="seq_kunjungan", name="SeqKunjungan", allocationSize = 1)
//    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SeqKunjungan")
    @GeneratedValue(generator = "uuidGen")
    @GenericGenerator(name = "uuidGen", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String idDatawp;
    private String idPegawai;
    private String keperluan;
    private String fgStatus;
    private String keterangan;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    private String lastUpdatedBy;
    private String npwp;
}
