package com.tamu.service;

import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.model.TransaksiModel;
import com.tamu.domain.model.datatables.mapping.DataTablesInput;
import com.tamu.domain.table.User;
import com.tamu.util.AppException;

import java.text.ParseException;

public interface ITransaksiService {
    ResponseModel getLastTransaction(String tgl, User user) throws ParseException;

    ResponseModel saveTransaksi(TransaksiModel transaksiModel, User user) throws AppException, ParseException;

    ResponseModel getListTransaksi(DataTablesInput dataTablesInput, User user);

    ResponseModel getTransaksiById(String idTransaksi, User user);

    ResponseModel deleteTransaksi(String idTransaksi, User user);
}
