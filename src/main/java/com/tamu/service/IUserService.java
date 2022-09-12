package com.tamu.service;

import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.model.UserFormModel;
import com.tamu.domain.model.datatables.mapping.DataTablesInput;
import com.tamu.domain.table.User;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public interface IUserService {

    User findByUsername(String username);

    ResponseModel getDaftarUser(DataTablesInput dataTablesInput, BigDecimal idUser, String level);

    ResponseModel saveUser(UserFormModel userFormModel, String idUserLogin, String levelUserLogin, HttpServletRequest request);

    ResponseModel deleteUser(String id, String idUserLogin);
}
