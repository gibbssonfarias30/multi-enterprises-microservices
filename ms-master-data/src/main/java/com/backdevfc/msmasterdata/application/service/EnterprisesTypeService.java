package com.backdevfc.msmasterdata.application.service;

import com.backdevfc.msmasterdata.domain.model.EnterprisesType;
import com.backdevfc.msmasterdata.domain.port.in.EnterprisesTypeIn;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class EnterprisesTypeService implements EnterprisesTypeIn {

    private final EnterprisesTypeIn enterprisesTypeIn;

    @Override
    public EnterprisesType create(EnterprisesType enterprisesType) {
        return enterprisesTypeIn.create(enterprisesType);
    }

    @Override
    public Optional<EnterprisesType> findById(Long id) {
        return enterprisesTypeIn.findById(id);
    }

    @Override
    public List<EnterprisesType> findAll() {
        return enterprisesTypeIn.findAll();
    }

    @Override
    public Optional<EnterprisesType> update(Long id, EnterprisesType enterprisesType) {
        return enterprisesTypeIn.update(id, enterprisesType);
    }

    @Override
    public Optional<EnterprisesType> delete(Long id) {
        return enterprisesTypeIn.delete(id);
    }
}
