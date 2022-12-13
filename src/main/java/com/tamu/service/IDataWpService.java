package com.tamu.service;

import com.tamu.domain.model.ProfilWpModel;
import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.table.User;

public interface IDataWpService {
    ResponseModel getDataWp(String npwp);

    ResponseModel saveDataWp(ProfilWpModel profilWpModel, User user);
}
