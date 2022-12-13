package com.tamu.service;

import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.table.DataWp;
import com.tamu.domain.table.Mfwp;

public interface ICetakanService {
    ResponseModel getCetakanWpOp(DataWp dataWp);
    ResponseModel getCetakanWpBadan(DataWp dataWp);
    ResponseModel getCetakanWpPemerintah(DataWp dataWp);
}
