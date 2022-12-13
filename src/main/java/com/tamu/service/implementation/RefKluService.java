package com.tamu.service.implementation;

import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.table.Pegawai;
import com.tamu.domain.table.RefKlu;
import com.tamu.repository.RefKluRepo;
import com.tamu.service.IRefKluService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
public class RefKluService implements IRefKluService {

    @Autowired
    private RefKluRepo refKluRepo;

    @Override
    public ResponseModel getAllKlu() {
        ResponseModel<List<RefKlu>> res = new ResponseModel<>("Get Data KLU");
        List<RefKlu> refKlus = refKluRepo.findAll();
        res.setObject(refKlus);
        return res;
    }

    @Override
    public ResponseModel getAllKluWithParam(String q) {
        ResponseModel<List<RefKlu>> res = new ResponseModel<>("Get Data KLU");
        List<RefKlu> refKlus = refKluRepo.findByKluContainingIgnoreCaseOrUraianContainingIgnoreCase(q,q);
        res.setObject(refKlus);
        return res;
    }
}
