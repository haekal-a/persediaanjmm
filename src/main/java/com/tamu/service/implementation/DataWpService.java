package com.tamu.service.implementation;

import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.table.Mfwp;
import com.tamu.repository.DataWpRepo;
import com.tamu.service.IDataWpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
public class DataWpService implements IDataWpService {

    @Autowired
    private DataWpRepo dataWpRepo;

    @Override
    public ResponseModel getDataWp(String npwp) {
        ResponseModel<Mfwp> res = new ResponseModel<>("Get Data WP");
        Mfwp dataWp = dataWpRepo.getByNpwp15(npwp);
        res.setObject(dataWp);
        return res;
    }
}
