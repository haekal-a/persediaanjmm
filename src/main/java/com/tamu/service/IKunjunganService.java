package com.tamu.service;

import com.tamu.domain.model.KunjunganModel;
import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.model.datatables.mapping.DataTablesInput;
import com.tamu.domain.table.User;

public interface IKunjunganService {
    ResponseModel saveKunjungan(KunjunganModel kunjunganModel, User user);

    ResponseModel getListKunjungan(DataTablesInput dataTablesInput, User user);

    ResponseModel updateKunjungan(String id, String status, String ket, User user);
}
