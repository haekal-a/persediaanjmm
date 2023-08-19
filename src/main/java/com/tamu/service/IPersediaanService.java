package com.tamu.service;

import com.tamu.domain.model.BarangModel;
import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.model.datatables.mapping.DataTablesInput;
import com.tamu.domain.table.User;

public interface IPersediaanService {
    ResponseModel saveReferensiBarang(BarangModel barangModel, User user);

    ResponseModel getListReferensiBarang(DataTablesInput dataTablesInput, User user);
    ResponseModel getListReferensiBarang(User user);
    ResponseModel getListBarangOrederByJumlahAsc(User user);
    ResponseModel getListBarangOrederByJumlahDesc(User user);

    ResponseModel deleteReferensiBarang(String id, String levelUserLogin);

    ResponseModel getAllMerk(User user);

    ResponseModel getAllJenisBarang(User user);

    ResponseModel getMonitoringPersediaan(DataTablesInput dataTablesInput, User user);
}
