package com.kondangan.controller.pmqa;

import com.kondangan.domain.model.MasterFormModel;
import com.kondangan.domain.model.ResponseModel;
import com.kondangan.domain.model.datatables.mapping.DataTablesInput;
import com.kondangan.domain.table.User;
import com.kondangan.service.IMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/pmqa/master")
public class MasterController {

    @Autowired
    private IMasterService masterService;

    @GetMapping("/")
    public ResponseModel getDaftarMaster(DataTablesInput dataTablesInput, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userLogin");
        return masterService.getDaftarMaster(dataTablesInput);
    }

    @PostMapping(value = "/")
    public ResponseModel saveMaster(@Valid final MasterFormModel masterFormModel, HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("userLogin");
        return masterService.saveMaster(masterFormModel, user.getId().toString());
    }

    @DeleteMapping(value = "/")
    public ResponseModel deleteMaster(@RequestParam String idMaster, HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("userLogin");
        return masterService.deleteMaster(idMaster);
    }

    @GetMapping("/get")
    public ResponseModel getMaster(@RequestParam String deliverableCode, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userLogin");
        return masterService.getMaster(deliverableCode);
    }
}
