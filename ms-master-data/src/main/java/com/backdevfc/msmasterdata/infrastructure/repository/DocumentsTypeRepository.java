package com.backdevfc.msmasterdata.infrastructure.repository;

import com.backdevfc.msmasterdata.infrastructure.entity.DocumentsTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentsTypeRepository extends JpaRepository<DocumentsTypeEntity, Long> {
}
