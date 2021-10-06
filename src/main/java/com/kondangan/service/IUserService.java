package com.kondangan.service;

import com.kondangan.domain.model.ResponseModel;
import com.kondangan.domain.model.UserFormModel;
import com.kondangan.domain.model.datatables.mapping.DataTablesInput;
import com.kondangan.domain.table.User;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public interface IUserService {

    User findByUsername(String username);

    ResponseModel getDaftarUser(DataTablesInput dataTablesInput, BigDecimal idUser, String level);

    ResponseModel saveUser(UserFormModel userFormModel, String idUserLogin, String levelUserLogin, HttpServletRequest request);

    ResponseModel deleteUser(String id, String idUserLogin);
}
