package com.backdevfc.msmasterdata.application.service;

import com.backdevfc.msmasterdata.domain.model.DocumentsType;
import com.backdevfc.msmasterdata.domain.port.in.DocumentsTypeIn;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class DocumentsTypeService implements DocumentsTypeIn {

    private final DocumentsTypeIn documentsTypeIn;

    @Override
    public DocumentsType create(DocumentsType documentType) {
        return documentsTypeIn.create(documentType);
    }

    @Override
    public Optional<DocumentsType> findById(Long id) {
        return documentsTypeIn.findById(id);
    }

    @Override
    public List<DocumentsType> findAll() {
        return documentsTypeIn.findAll();
    }

    @Override
    public Optional<DocumentsType> update(Long id, DocumentsType documentType) {
        return documentsTypeIn.update(id, documentType);
    }

    @Override
    public Optional<DocumentsType> delete(Long id) {
        return documentsTypeIn.delete(id);
    }
}
