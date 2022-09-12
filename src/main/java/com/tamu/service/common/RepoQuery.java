package com.tamu.service.common;

public final class RepoQuery {
    public static final String DOCUMENT_FILE_GET_BY_ID_DETAIL_DOCUMENT =
            " SELECT a.idDocument as idDocument, a.idDetailDocument as idDetailDocument, a.documentName as documentName, a.idJenisDocument as idJenisDocument, a.path as path, b.nmDocument as nmDocument " +
                    " FROM DocumentFile a, JenisDocument b " +
            " WHERE a.idJenisDocument = b.idJenisDocument " +
            " AND a.idDetailDocument = ?1 ";

    public static final String COUNT_TOTAL_MONITORING_BY_POSITION_GROUP_BY_PERIOD =
            " SELECT new com.kondangan.domain.model.MonitoringCountModel(a.period, COUNT(a.period)) " +
                    " FROM Monitoring AS a " +
                    " WHERE (?1 is null or a.position = ?1) " +
                    " AND a.monthSubmission is not null " +
                    " GROUP BY a.period ";

    public static final String COUNT_TOTAL_MONITORING_GROUP_BY_POSITION =
            " SELECT new com.kondangan.domain.model.MonitoringCountModel(a.position, COUNT(a.position)) " +
                    " FROM Monitoring AS a " +
                    " WHERE a.monthSubmission is not null " +
                    " GROUP BY a.position ";

    public static final String COUNT_TOTAL_MONITORING_BY_PAYMENT_GROUP_BY_PERIOD =
            " SELECT new com.kondangan.domain.model.MonitoringCountModel(a.period, COUNT(a.period)) " +
                    " FROM Monitoring AS a " +
                    " WHERE (?1 is null or a.paymentStatus = ?1) " +
                    " AND a.monthSubmission is not null " +
                    " GROUP BY a.period ";

    public static final String COUNT_TOTAL_MONITORING_GROUP_BY_PAYMENT =
            " SELECT new com.kondangan.domain.model.MonitoringCountModel(a.paymentStatus, COUNT(a.paymentStatus)) " +
                    " FROM Monitoring AS a " +
                    " WHERE a.monthSubmission is not null " +
                    " GROUP BY a.paymentStatus ";
}
