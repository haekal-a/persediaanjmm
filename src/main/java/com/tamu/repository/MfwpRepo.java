package com.tamu.repository;

import com.tamu.domain.table.Mfwp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MfwpRepo extends JpaRepository<Mfwp, String> {
    Mfwp getByNpwp15(String npwp);
}
