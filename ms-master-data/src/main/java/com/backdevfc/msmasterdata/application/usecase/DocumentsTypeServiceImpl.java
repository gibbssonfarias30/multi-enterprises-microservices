package com.backdevfc.msmasterdata.application.usecase;

import com.backdevfc.msmasterdata.domain.model.DocumentsType;
import com.backdevfc.msmasterdata.domain.port.in.DocumentsTypeIn;
import com.backdevfc.msmasterdata.domain.port.out.DocumentsTypeOut;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class DocumentsTypeServiceImpl implements DocumentsTypeIn {

    private final DocumentsTypeOut documentsTypeOut;

    @Override
    public DocumentsType create(DocumentsType documentType) {
        return documentsTypeOut.createDocumentType(documentType);
    }

    @Override
    public Optional<DocumentsType> findById(Long id) {
        return documentsTypeOut.findOneDocumentType(id);
    }

    @Override
    public List<DocumentsType> findAll() {
        return documentsTypeOut.findAllDocumentTypes();
    }

    @Override
    public Optional<DocumentsType> update(Long id, DocumentsType documentType) {
        return documentsTypeOut.updateDocumentType(id, documentType);
    }

    @Override
    public Optional<DocumentsType> delete(Long id) {
        return documentsTypeOut.deleteDocumentType(id);
    }
}
