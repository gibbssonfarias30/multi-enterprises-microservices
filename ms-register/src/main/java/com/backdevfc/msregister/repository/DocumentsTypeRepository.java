package com.backdevfc.msregister.repository;

import com.backdevfc.msregister.entity.DocumentsTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentsTypeRepository extends JpaRepository<DocumentsTypeEntity, Integer> {
    DocumentsTypeEntity findByCodType(String code);
}
