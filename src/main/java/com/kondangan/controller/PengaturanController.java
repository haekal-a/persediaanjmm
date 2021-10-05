package com.kondangan.controller;

import com.kondangan.domain.model.ResponseModel;
import com.kondangan.domain.model.UserFormModel;
import com.kondangan.domain.model.datatables.mapping.DataTablesInput;
import com.kondangan.domain.table.User;
import com.kondangan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/pengaturan")
public class PengaturanController {
    
    @Autowired
    private IUserService userService;

    @GetMapping("/role/")
    public ResponseModel getDaftarUser(DataTablesInput dataTablesInput, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userLogin");
        return userService.getDaftarUser(dataTablesInput, user.getId(), user.getLevel());
    }

    @PostMapping(value = "/role/")
    public ResponseModel saveUser(@Valid final UserFormModel UserFormModel, HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("userLogin");
        return userService.saveUser(UserFormModel, user.getId().toString(), request);
    }

    @DeleteMapping(value = "/role/")
    public ResponseModel deleteUser(@RequestParam String id, HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("userLogin");
        return userService.deleteUser(id, user.getId().toString());
    }
}
