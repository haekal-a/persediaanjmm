package com.tamu.repository;

import com.tamu.domain.table.RefKlu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefKluRepo extends JpaRepository<RefKlu, String> {
    List<RefKlu> findByKluContainingIgnoreCaseOrUraianContainingIgnoreCase(String klu, String uraian);
}
