package com.backdevfc.msmasterdata.domain.port.out;

import com.backdevfc.msmasterdata.domain.model.EnterprisesType;

import java.util.List;
import java.util.Optional;

public interface EnterprisesTypeOut {
    EnterprisesType createEnterprisesType(EnterprisesType enterprisesType);
    Optional<EnterprisesType> findOneEnterprisesType(Long id);
    List<EnterprisesType> findAllEnterprisesTypes();
    Optional<EnterprisesType> updateEnterprisesType(Long id, EnterprisesType enterprisesType);
    Optional<EnterprisesType> deleteEnterprisesType(Long id); // UPDATE STATUS
}
