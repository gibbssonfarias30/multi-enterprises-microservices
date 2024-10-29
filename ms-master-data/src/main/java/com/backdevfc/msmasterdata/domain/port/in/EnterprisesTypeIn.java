package com.backdevfc.msmasterdata.domain.port.in;



import com.backdevfc.msmasterdata.domain.model.EnterprisesType;

import java.util.List;
import java.util.Optional;

public interface EnterprisesTypeIn {
    EnterprisesType create(EnterprisesType enterprisesType);
    Optional<EnterprisesType> findById(Long id);
    List<EnterprisesType> findAll();
    Optional<EnterprisesType> update(Long id, EnterprisesType enterprisesType);
    Optional<EnterprisesType> delete(Long id); // UPDATE STATUS
}
