package com.backdevfc.msregister.util;

import com.backdevfc.msregister.aggregates.enums.ETypeDocuments;
import com.backdevfc.msregister.aggregates.request.RequestPersons;
import com.backdevfc.msregister.aggregates.constants.Constants;
import com.backdevfc.msregister.entity.DocumentsTypeEntity;
import com.backdevfc.msregister.repository.DocumentsTypeRepository;
import com.backdevfc.msregister.repository.EnterprisesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PersonsValidations {

    private final DocumentsTypeRepository documentsTypeRepository;
    private final EnterprisesRepository enterprisesRepository;

    public boolean validateInput(RequestPersons requestPersons){
        if (requestPersons == null){
            return false;
        }
        DocumentsTypeEntity documentsType = documentsTypeRepository.findByCodType(ETypeDocuments.DNI.getValue());
        if(requestPersons.getDocuments_type_id_documents_type() != Integer.parseInt(documentsType.getCodType())
                || requestPersons.getNumDocument().length() != Constants.LENGTH_DNI) {
            return false;
        }
        if (isNullOrEmpty(requestPersons.getEmail()) || isNullOrEmpty(requestPersons.getNumDocument())
                || isNullOrEmpty(requestPersons.getTelephone())) {
            return false;
        }
        return enterprisesRepository.existsById(requestPersons.getEnterprises_id_enterprises());
    }

    public boolean isNullOrEmpty(String data) {
        return data == null || data.isEmpty();
    }

}
