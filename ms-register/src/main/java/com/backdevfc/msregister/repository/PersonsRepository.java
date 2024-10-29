package com.backdevfc.msregister.repository;

import com.backdevfc.msregister.entity.PersonsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonsRepository extends JpaRepository<PersonsEntity, Integer> {
}
