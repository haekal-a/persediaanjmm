package com.tamu.repository;

import com.tamu.domain.table.DataWp;
import com.tamu.domain.table.Mfwp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataWpRepo extends JpaRepository<DataWp, String> {
    DataWp getByNpwp15(String npwp);
}
