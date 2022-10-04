package com.tamu.service;

import com.tamu.domain.model.KunjunganModel;
import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.table.User;

public interface IKunjunganService {
    ResponseModel saveKunjungan(KunjunganModel kunjunganModel, User user);
}
