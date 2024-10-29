package com.backdevfc.msmasterdata.infrastructure.repository.adapter;

import com.backdevfc.msmasterdata.domain.model.DocumentsType;
import com.backdevfc.msmasterdata.domain.port.out.DocumentsTypeOut;
import com.backdevfc.msmasterdata.infrastructure.entity.DocumentsTypeEntity;
import com.backdevfc.msmasterdata.infrastructure.repository.DocumentsTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Component
public class DocumentsTypeRepositoryAdapter implements DocumentsTypeOut {

    private final DocumentsTypeRepository repository;

    @Override
    public DocumentsType createDocumentType(DocumentsType documentType) {
        DocumentsTypeEntity typeEntity = DocumentsTypeEntity.fromDomainModel(documentType);
        DocumentsTypeEntity type = getEntity(documentType, typeEntity);
        return repository.save(type).toDomainModel();
    }

    @Override
    public Optional<DocumentsType> findOneDocumentType(Long id) {
        return repository.findById(id).map(DocumentsTypeEntity::toDomainModel);
    }

    @Override
    public List<DocumentsType> findAllDocumentTypes() {
        return repository.findAll().stream()
                .map(DocumentsTypeEntity::toDomainModel)
                .toList();
    }

    @Override
    public Optional<DocumentsType> updateDocumentType(Long id, DocumentsType documentType) {
        if (repository.existsById(id)) {
            DocumentsTypeEntity typeEntity = DocumentsTypeEntity.fromDomainModel(documentType);
            return Optional.of(repository.save(typeEntity).toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public Optional<DocumentsType> deleteDocumentType(Long id) {
        if (repository.existsById(id)) {
            Optional<DocumentsTypeEntity> typeEntity = repository.findById(id);
            typeEntity.get().setStatus(0);
            return Optional.of(repository.save(typeEntity.get()).toDomainModel());
        }
        return Optional.empty();
    }

    private DocumentsTypeEntity getEntity(DocumentsType documentsType, DocumentsTypeEntity documentsTypeEntity) {
        documentsTypeEntity.setStatus(1);
        documentsTypeEntity.setUserCreate("GFARIAS30");
        documentsTypeEntity.setDateCreate(getTimestamp());
        return documentsTypeEntity;
    }
    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }
}
