package com.tamu.repository;

import com.tamu.domain.table.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface UserRepo extends JpaRepository<User, BigDecimal> {
    User findByUsername(String user);
}
