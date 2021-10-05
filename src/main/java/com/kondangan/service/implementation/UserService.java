package com.kondangan.service.implementation;

import com.kondangan.controller.exception.AppErrorException;
import com.kondangan.domain.model.ResponseModel;
import com.kondangan.domain.model.UserFormModel;
import com.kondangan.domain.model.datatables.mapping.DataTablesInput;
import com.kondangan.domain.model.datatables.mapping.DataTablesOutput;
import com.kondangan.domain.table.Master;
import com.kondangan.domain.table.User;
import com.kondangan.repository.UserRepo;
import com.kondangan.service.IUserService;
import com.kondangan.service.common.UtilityService;
import com.kondangan.util.AppException;
import com.kondangan.util.Constanta;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
public class UserService implements IUserService {

    private final UserRepo userRepo;
    private final UtilityService utilityService;

    @Value("${app.path.root}")
    private String root;

    @Autowired
    public UserService(UserRepo userRepo, UtilityService utilityService) {
        this.userRepo = userRepo;
        this.utilityService = utilityService;
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public ResponseModel getDaftarUser(DataTablesInput dataTablesInput, BigDecimal idUser, String level) {
        List<User> users = new ArrayList<>();
        if (!level.equals(Constanta.LEVEL_ADMIN)){
            User user = userRepo.findById(idUser).get();
            users.add(user);
        } else {
            users = userRepo.findAll();
        }
        DataTablesOutput output = utilityService.dataTablesOutputWrapper(users, (long) users.size(), dataTablesInput.getDraw());
        ResponseModel<DataTablesOutput> res = new ResponseModel("Daftar User");
        res.setObject(output);
        return res;
    }

    @Override
    public ResponseModel saveUser(UserFormModel userFormModel, String idUserLogin, HttpServletRequest request) {
        User user;
        if (Objects.equals(userFormModel.getId(), "")){
            user = new User();
            user.setCreatedBy(idUserLogin);
            user.setCreationDate(new Date());
        } else {
            user = userRepo.getById(new BigDecimal(userFormModel.getId()));
            user.setLastUpdateBy(idUserLogin);
            user.setLastUpdateDate(new Date());
        }
        user.setUsername(userFormModel.getUsername());
        user.setNama(userFormModel.getNama());
        user.setLevel(userFormModel.getLevel());
        user.setPassword(DigestUtils.md5Hex(userFormModel.getPassword() + Constanta.SALT));
        User userNew = userRepo.save(user);
        // update user session
        if (userNew.getId().toString().equals(idUserLogin)){
            request.getSession().setAttribute("userLogin", userNew);
        }
        return new ResponseModel("Save User");
    }

    @Override
    public ResponseModel deleteUser(String id, String idUserLogin) {
        if (id.equals(idUserLogin)){
            throw new AppException(1, "User ini tidak dapat dihapus!");
        }
        User user = userRepo.getById(new BigDecimal(id));
        userRepo.delete(user);
        return new ResponseModel("Delete User");
    }
}
