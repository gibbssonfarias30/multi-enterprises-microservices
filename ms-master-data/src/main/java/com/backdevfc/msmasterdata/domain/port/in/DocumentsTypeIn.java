package com.backdevfc.msmasterdata.domain.port.in;

import com.backdevfc.msmasterdata.domain.model.DocumentsType;

import java.util.List;
import java.util.Optional;

public interface DocumentsTypeIn {
    DocumentsType create(DocumentsType documentType);
    Optional<DocumentsType> findById(Long id);
    List<DocumentsType> findAll();
    Optional<DocumentsType> update(Long id, DocumentsType documentType);
    Optional<DocumentsType> delete(Long id); // UPDATE STATUS
}
