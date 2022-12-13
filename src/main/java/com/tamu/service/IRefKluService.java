package com.tamu.service;

import com.tamu.domain.model.ResponseModel;

public interface IRefKluService {
    public ResponseModel getAllKlu();
    public ResponseModel getAllKluWithParam(String q);
}
