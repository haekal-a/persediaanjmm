package com.kondangan.service;

import com.kondangan.domain.table.User;

public interface IUserService {

    User findByUsername(String username);
}
