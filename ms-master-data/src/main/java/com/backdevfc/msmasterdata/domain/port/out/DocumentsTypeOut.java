package com.backdevfc.msmasterdata.domain.port.out;

import com.backdevfc.msmasterdata.domain.model.DocumentsType;

import java.util.List;
import java.util.Optional;

public interface DocumentsTypeOut {
    DocumentsType createDocumentType(DocumentsType documentType);
    Optional<DocumentsType> findOneDocumentType(Long id);
    List<DocumentsType> findAllDocumentTypes();
    Optional<DocumentsType> updateDocumentType(Long id, DocumentsType documentType);
    Optional<DocumentsType> deleteDocumentType(Long id); // UPDATE STATUS
}
