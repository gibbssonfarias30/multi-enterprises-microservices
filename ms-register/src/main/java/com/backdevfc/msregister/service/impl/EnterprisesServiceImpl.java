package com.backdevfc.msregister.service.impl;

import com.backdevfc.msregister.aggregates.request.RequestEnterprises;
import com.backdevfc.msregister.aggregates.response.ResponseBase;
import com.backdevfc.msregister.aggregates.constants.Constants;
import com.backdevfc.msregister.aggregates.response.ResponseSunat;
import com.backdevfc.msregister.client.SunatClient;
import com.backdevfc.msregister.config.RedisService;
import com.backdevfc.msregister.entity.DocumentsTypeEntity;
import com.backdevfc.msregister.entity.EnterprisesEntity;
import com.backdevfc.msregister.entity.EnterprisesTypeEntity;
import com.backdevfc.msregister.repository.DocumentsTypeRepository;
import com.backdevfc.msregister.repository.EnterprisesRepository;
import com.backdevfc.msregister.service.EnterprisesService;
import com.backdevfc.msregister.util.EnterprisesValidations;
import com.backdevfc.msregister.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class EnterprisesServiceImpl implements EnterprisesService {

    private final SunatClient sunatClient;
    private final EnterprisesRepository enterprisesRepository;
    private final DocumentsTypeRepository documentsTypeRepository;
    private final RedisService redisService;
    private final EnterprisesValidations enterprisesValidations;

    @Value("${token.api}")
    private String tokenSunat;

    @Value("${time.expiration.info}")
    private String timeExpirationSunat;


    @Override
    public ResponseBase createEnterprise(RequestEnterprises requestEnterprises) {
        boolean validate = enterprisesValidations.validateInput(requestEnterprises);
        if (validate) {
            EnterprisesEntity enterprises = getEntity(requestEnterprises);
            enterprisesRepository.save(enterprises);
            return new ResponseBase(Constants.CODE_SUCCESS, Constants.MESS_SUCCESS, Optional.of(enterprises));
        }
        return new ResponseBase(Constants.CODE_ERROR_DATA_INPUT, Constants.MESS_ERROR_DATA_NOT_VALID, Optional.empty());
    }

    @Override
    public ResponseBase findOneEnterprise(String ruc) {
        String redisCache = redisService.getValueByKey(Constants.REDIS_KEY_INFO_SUNAT + ruc);
        if (redisCache != null) {
            EnterprisesEntity entity = Util.convertFromJson(redisCache, EnterprisesEntity.class);
            assert entity != null;
            return new ResponseBase(Constants.CODE_SUCCESS, Constants.MESS_SUCCESS, Optional.of(entity));
        } else {
            EnterprisesEntity enterprisesEntity = enterprisesRepository.findByNumDocument(ruc);
            if (enterprisesEntity != null) {
                String redisData = Util.convertToJson(enterprisesEntity);
                redisService.saveKeyValue(Constants.REDIS_KEY_INFO_SUNAT + ruc, redisData, Integer.parseInt(timeExpirationSunat));
                return new ResponseBase(Constants.CODE_SUCCESS, Constants.MESS_SUCCESS, Optional.of(enterprisesEntity));
            }
        }
        return new ResponseBase(Constants.CODE_ERROR_DATA_NOT, Constants.MESS_NON_DATA, Optional.empty());
    }

    @Override
    public ResponseBase findAllEnterprises() {
        Optional enterprises = Optional.of(enterprisesRepository.findAll());
        if (enterprises.isPresent()) {
            return new ResponseBase(Constants.CODE_SUCCESS, Constants.MESS_ZERO_ROWS, Optional.empty());
        }
        return new ResponseBase(Constants.CODE_SUCCESS, Constants.MESS_SUCCESS, enterprises);
    }

    @Override
    public ResponseBase updateEnterprise(Integer id, RequestEnterprises requestEnterprises) {
        boolean existEnterprise = enterprisesRepository.existsById(id);
        if (existEnterprise) {
            Optional<EnterprisesEntity> enterprises = enterprisesRepository.findById(id);
            boolean validateEntity = enterprisesValidations.validateInputUpdate(requestEnterprises);
            if (validateEntity) {
                EnterprisesEntity enterprisesUpdate = getEntityUpdate(requestEnterprises, Objects.requireNonNull(enterprises.orElse(null)));
                enterprisesRepository.save(enterprisesUpdate);
                return new ResponseBase(Constants.CODE_SUCCESS, Constants.MESS_SUCCESS, Optional.of(enterprisesUpdate));
            }
            return new ResponseBase(Constants.CODE_ERROR_DATA_INPUT, Constants.MESS_ERROR_DATA_NOT_VALID, Optional.empty());
        }
        return new ResponseBase(Constants.CODE_ERROR_DATA_NOT, Constants.MESS_ERROR_NOT_UPDATE, Optional.empty());
    }

    @Override
    public ResponseBase deleteEnterprise(Integer id) {
        boolean existEnterprise = enterprisesRepository.existsById(id);
        if (existEnterprise) {
            EnterprisesEntity enterprisesEntity = enterprisesRepository.findById(id).orElseThrow();
            enterprisesRepository.save(getEntityDelete(enterprisesEntity));
            return new ResponseBase(Constants.CODE_SUCCESS, Constants.MESS_SUCCESS, Optional.of(enterprisesEntity));
        }
        return new ResponseBase(Constants.CODE_ERROR, Constants.MESS_ERROR_DATA_NOT_VALID, Optional.empty());
    }


    private Timestamp getTimestamp() {
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }

    private EnterprisesEntity getEntity(RequestEnterprises requestEnterprises){
        EnterprisesEntity entity = new EnterprisesEntity();
        ResponseSunat sunat = getExecutionSunat(requestEnterprises.getNumDocument());
        if(sunat != null){
            entity.setNumDocument(sunat.getNumeroDocumento());
            entity.setBusinessName(sunat.getRazonSocial());
            entity.setTradeName(enterprisesValidations.isNullOrEmpty(requestEnterprises.getTradeName()) ? sunat.getRazonSocial() : requestEnterprises.getTradeName());
        }
        entity.setStatus(Constants.STATUS_ACTIVE);
        entity.setEnterprisesTypeEntity(getEnterprisesType(requestEnterprises));
        entity.setDocumentsTypeEntity(getDocumentsType());
        entity.setUserCreate(Constants.AUDIT_ADMIN);
        entity.setDateCreate(getTimestamp());

        return entity;
    }
    private EnterprisesEntity getEntityUpdate(RequestEnterprises requestEnterprises, EnterprisesEntity enterprisesEntity){
        enterprisesEntity.setNumDocument(requestEnterprises.getNumDocument());
        enterprisesEntity.setBusinessName(requestEnterprises.getBusinessName());
        ResponseSunat sunat = getExecutionSunat(requestEnterprises.getNumDocument());
        if(sunat != null){
            enterprisesEntity.setNumDocument(sunat.getNumeroDocumento());
            enterprisesEntity.setBusinessName(sunat.getRazonSocial());
            enterprisesEntity.setTradeName(enterprisesValidations.isNullOrEmpty(requestEnterprises.getTradeName()) ? sunat.getRazonSocial() : requestEnterprises.getTradeName());
        }
        enterprisesEntity.setEnterprisesTypeEntity(getEnterprisesType(requestEnterprises));
        enterprisesEntity.setUserModif(Constants.AUDIT_ADMIN);
        enterprisesEntity.setDateModif(getTimestamp());
        return enterprisesEntity;
    }

    private EnterprisesEntity getEntityDelete(EnterprisesEntity enterprisesEntity){
        enterprisesEntity.setStatus(Constants.STATUS_INACTIVE);
        enterprisesEntity.setUserDelete(Constants.AUDIT_ADMIN);
        enterprisesEntity.setDateDelete(getTimestamp());
        return enterprisesEntity;
    }

    private EnterprisesTypeEntity getEnterprisesType(RequestEnterprises request) {
        EnterprisesTypeEntity enterprisesTypeEntity = new EnterprisesTypeEntity();
        enterprisesTypeEntity.setIdEnterprisesType(request.getEnterprisesTypeEntity());
        return enterprisesTypeEntity;
    }

    private DocumentsTypeEntity getDocumentsType() {
        return documentsTypeRepository.findByCodType(Constants.COD_TYPE_RUC);
    }

    public ResponseSunat getExecutionSunat(String ruc) {
        String authorization = Constants.BEARER_PREFIX + tokenSunat;
        return sunatClient.getInfoSunat(ruc, authorization);
    }
}
