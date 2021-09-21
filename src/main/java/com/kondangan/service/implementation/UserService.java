package com.kondangan.service.implementation;

import com.kondangan.domain.table.User;
import com.kondangan.repository.UserRepo;
import com.kondangan.service.IUserService;
import com.kondangan.service.common.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
