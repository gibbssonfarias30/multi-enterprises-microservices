package com.backdevfc.msmasterdata.application.usecase;

import com.backdevfc.msmasterdata.domain.model.EnterprisesType;
import com.backdevfc.msmasterdata.domain.port.in.EnterprisesTypeIn;
import com.backdevfc.msmasterdata.domain.port.out.EnterprisesTypeOut;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class EnterprisesTypeServiceImpl implements EnterprisesTypeIn {

    private final EnterprisesTypeOut enterprisesTypeOut;

    @Override
    public EnterprisesType create(EnterprisesType enterprisesType) {
        return enterprisesTypeOut.createEnterprisesType(enterprisesType);
    }

    @Override
    public Optional<EnterprisesType> findById(Long id) {
        return enterprisesTypeOut.findOneEnterprisesType(id);
    }

    @Override
    public List<EnterprisesType> findAll() {
        return enterprisesTypeOut.findAllEnterprisesTypes();
    }

    @Override
    public Optional<EnterprisesType> update(Long id, EnterprisesType enterprisesType) {
        return enterprisesTypeOut.updateEnterprisesType(id, enterprisesType);
    }

    @Override
    public Optional<EnterprisesType> delete(Long id) {
        return enterprisesTypeOut.deleteEnterprisesType(id);
    }
}
