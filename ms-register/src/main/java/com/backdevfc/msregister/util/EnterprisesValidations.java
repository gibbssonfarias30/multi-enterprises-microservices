package com.backdevfc.msregister.util;

import com.backdevfc.msregister.aggregates.enums.ETypeDocuments;
import com.backdevfc.msregister.aggregates.request.RequestEnterprises;
import com.backdevfc.msregister.aggregates.constants.Constants;
import com.backdevfc.msregister.entity.DocumentsTypeEntity;
import com.backdevfc.msregister.repository.DocumentsTypeRepository;
import com.backdevfc.msregister.repository.EnterprisesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class EnterprisesValidations {

    private final DocumentsTypeRepository typeRepository;
    private final EnterprisesRepository enterprisesRepository;

    public boolean validateInput(RequestEnterprises requestEnterprises){
        if(requestEnterprises == null){
            return false;
        }
//        DocumentsTypeEntity documentType = typeRepository.findByCodType("02");
//        log.info("DATO: {} - DATO: {}", Integer.valueOf(documentType.getCodType()), requestEnterprises.getEnterprisesTypeEntity());
        if(requestEnterprises.getDocumentsTypeEntity() != Integer.parseInt(ETypeDocuments.RUC.getValue())
                || requestEnterprises.getNumDocument().length() != Constants.LENGTH_RUC){
            return false;
        }
        if(isNullOrEmpty(requestEnterprises.getNumDocument())){
            return false;
        }
        if(existEnterprise(requestEnterprises.getNumDocument())){
            return false;
        }

        return true;
    }
    public boolean validateInputUpdate(RequestEnterprises requestEnterprises){
        if(requestEnterprises == null){
            return false;
        }
        DocumentsTypeEntity documentType = typeRepository.findByCodType(Constants.COD_TYPE_RUC);
        if(requestEnterprises.getDocumentsTypeEntity() != Integer.parseInt(documentType.getCodType())
                || requestEnterprises.getNumDocument().length() != Constants.LENGTH_RUC){
            return false;
        }
        if(isNullOrEmpty(requestEnterprises.getBusinessName()) && isNullOrEmpty(requestEnterprises.getNumDocument()) && isNullOrEmpty(requestEnterprises.getTradeName())){
            return false;
        }
        return true;
    }

    public boolean existEnterprise(String numDocument){
        return enterprisesRepository.existsByNumDocument(numDocument);
    }

    public boolean isNullOrEmpty(String data) {
        return data == null || data.isEmpty();
    }
}
