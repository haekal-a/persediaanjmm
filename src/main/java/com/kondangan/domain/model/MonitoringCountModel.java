package com.kondangan.domain.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class MonitoringCountModel implements Serializable {
    private Integer period;
    private String paymentStatus;
    private Long total;

    public MonitoringCountModel(Integer period, Long total) {
        this.period = period;
        this.total = total;
    }

    public MonitoringCountModel(String paymentStatus, Long total) {
        this.paymentStatus = paymentStatus;
        this.total = total;
    }
}
