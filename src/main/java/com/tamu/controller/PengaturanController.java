package com.tamu.controller;

import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.model.UserFormModel;
import com.tamu.domain.model.datatables.mapping.DataTablesInput;
import com.tamu.domain.table.User;
import com.tamu.service.IUserService;
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
        return userService.saveUser(UserFormModel, user.getId(), user.getLevel(), request);
    }

    @DeleteMapping(value = "/role/")
    public ResponseModel deleteUser(@RequestParam String id, HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("userLogin");
        return userService.deleteUser(id, user.getId());
    }
}
