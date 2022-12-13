package com.tamu.domain.table;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name="ref_klu", schema = "public")
public class RefKlu implements Serializable {

    @Id
    private String klu;
    private String uraian;
}
