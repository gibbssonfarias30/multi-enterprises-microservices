package com.backdevfc.msregister.repository;

import com.backdevfc.msregister.entity.EnterprisesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterprisesRepository extends JpaRepository<EnterprisesEntity, Integer> {
    EnterprisesEntity findByNumDocument(String ruc);

    boolean existsByNumDocument(String numDocument);
}
