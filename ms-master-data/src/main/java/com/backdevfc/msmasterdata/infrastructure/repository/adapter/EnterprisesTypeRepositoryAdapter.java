package com.backdevfc.msmasterdata.infrastructure.repository.adapter;

import com.backdevfc.msmasterdata.domain.model.EnterprisesType;
import com.backdevfc.msmasterdata.domain.port.out.EnterprisesTypeOut;
import com.backdevfc.msmasterdata.infrastructure.entity.EnterprisesTypeEntity;
import com.backdevfc.msmasterdata.infrastructure.repository.EnterprisesTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Component
public class EnterprisesTypeRepositoryAdapter implements EnterprisesTypeOut {

    private final EnterprisesTypeRepository repository;

    @Override
    public EnterprisesType createEnterprisesType(EnterprisesType enterprisesType) {
        EnterprisesTypeEntity typeEntity = EnterprisesTypeEntity.fromDomainModel(enterprisesType);
        return repository.save(typeEntity).toDomainModel();
    }

    @Override
    public Optional<EnterprisesType> findOneEnterprisesType(Long id) {
        return repository.findById(id).map(EnterprisesTypeEntity::toDomainModel);
    }

    @Override
    public List<EnterprisesType> findAllEnterprisesTypes() {
        return repository.findAll()
                .stream()
                .map(EnterprisesTypeEntity::toDomainModel)
                .toList();
    }

    @Override
    public Optional<EnterprisesType> updateEnterprisesType(Long id, EnterprisesType enterprisesType) {
        if (repository.existsById(id)) {
            EnterprisesTypeEntity typeEntity = EnterprisesTypeEntity.fromDomainModel(enterprisesType);
            return Optional.of(repository.save(typeEntity).toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public Optional<EnterprisesType> deleteEnterprisesType(Long id) {
        if (repository.existsById(id)) {
            Optional<EnterprisesTypeEntity> typeEntity = repository.findById(id);
            typeEntity.get().setStatus(0);
            return Optional.of(repository.save(typeEntity.get()).toDomainModel());
        }
        return Optional.empty();
    }
}
