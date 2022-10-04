package com.tamu.service;

import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.table.Mfwp;

public interface ICetakanService {
    ResponseModel getCetakanWpOp(Mfwp mfwp);
    ResponseModel getCetakanWpBadan(Mfwp mfwp);
    ResponseModel getCetakanWpPemerintah(Mfwp mfwp);
}
