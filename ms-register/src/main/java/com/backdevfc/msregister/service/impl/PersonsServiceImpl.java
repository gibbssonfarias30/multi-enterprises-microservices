package com.backdevfc.msregister.service.impl;


import com.backdevfc.msregister.aggregates.request.RequestPersons;
import com.backdevfc.msregister.aggregates.response.ResponseBase;
import com.backdevfc.msregister.aggregates.response.ResponseReniec;
import com.backdevfc.msregister.client.ReniecClient;
import com.backdevfc.msregister.config.RedisService;
import com.backdevfc.msregister.aggregates.constants.Constants;
import com.backdevfc.msregister.entity.DocumentsTypeEntity;
import com.backdevfc.msregister.entity.EnterprisesEntity;
import com.backdevfc.msregister.entity.PersonsEntity;
import com.backdevfc.msregister.repository.PersonsRepository;
import com.backdevfc.msregister.service.PersonsService;
import com.backdevfc.msregister.util.PersonsValidations;
import com.backdevfc.msregister.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PersonsServiceImpl implements PersonsService {

    private final ReniecClient reniecClient;
    private final PersonsRepository personsRepository;
    private final PersonsValidations personsValidations;
    private final RedisService redisService;


    @Value("${token.api}")
    private String tokenReniec;

    @Value("${time.expiration.info}")
    private String timeExpirationReniec;


    @Override
    public ResponseBase getInfoReniec(String numero) {
        ResponseReniec responseReniec = getExecutionReniec(numero);
        if (responseReniec != null){
            return new ResponseBase(Constants.CODE_SUCCESS, Constants.MESS_SUCCESS, Optional.of(responseReniec));
        }
        return new ResponseBase(Constants.CODE_ERROR, Constants.MESS_NON_DATA_RENIEC, Optional.empty());
    }



    @Override
    public ResponseBase createPersons(RequestPersons requestPersons) {
        boolean validatePersons = personsValidations.validateInput(requestPersons);
        if (validatePersons){
            PersonsEntity personsEntity = getPersonsEntity(requestPersons);
            if (personsEntity != null){
                personsRepository.save(personsEntity);
                return new ResponseBase(Constants.CODE_SUCCESS, Constants.MESS_SUCCESS, Optional.of(personsEntity));
            }
            return new ResponseBase(Constants.CODE_ERROR, Constants.MESS_ERROR, Optional.empty());
        }
        return new ResponseBase(Constants.CODE_ERROR, Constants.MESS_ERROR_DATA_NOT_VALID, Optional.empty());
    }

    @Override
    public ResponseBase findOnePerson(Integer id) {
        Optional<PersonsEntity> persons = personsRepository.findById(id);
        if (persons.isPresent()) {
            return new ResponseBase(Constants.CODE_SUCCESS, Constants.MESS_SUCCESS, Optional.of(persons.get()));
        }
        return new ResponseBase(Constants.CODE_SUCCESS, Constants.MESS_ZERO_ROWS, Optional.empty());
    }

    @Override
    public ResponseBase findAll() {
        List<PersonsEntity> entityList = personsRepository.findAll();
        if (!entityList.isEmpty()) {
            return new ResponseBase(Constants.CODE_SUCCESS, Constants.MESS_SUCCESS, Optional.of(entityList));
        }
        return new ResponseBase(Constants.CODE_SUCCESS, Constants.MESS_ZERO_ROWS, Optional.empty());
    }

    @Override
    public ResponseBase updatePersons(Integer id, RequestPersons requestPersons) {
        boolean existsPerson = personsRepository.existsById(id);
        if(existsPerson){
            Optional<PersonsEntity> personsEntity = personsRepository.findById(id);
            boolean validatInput = personsValidations.validateInput(requestPersons);
            if(validatInput){
                PersonsEntity personsUpdate = getPerson(requestPersons,personsEntity.get(),true);
                personsRepository.save(personsUpdate);
                return new ResponseBase(Constants.CODE_SUCCESS,Constants.MESS_SUCCESS,Optional.of(personsUpdate));
            }
            return new ResponseBase(Constants.CODE_ERROR,Constants.MESS_ERROR_DATA_NOT_VALID,Optional.empty());
        }
        return new ResponseBase(Constants.CODE_ERROR,Constants.MESS_ERROR_NOT_UPDATE_PERSON,Optional.empty());
    }

    private PersonsEntity getPersonsEntity(RequestPersons requestPersons){
        PersonsEntity personsEntity = new PersonsEntity();
        ResponseReniec reniec = getExecutionReniec(requestPersons.getNumDocument());
        if(reniec != null){
            personsEntity.setName(reniec.getNombres());
            personsEntity.setLastname(reniec.getApellidoPaterno() + " " + reniec.getApellidoMaterno());
        }else{
            return null;
        }
        return  getPerson(requestPersons,personsEntity,false);
    }

    private PersonsEntity getPerson(RequestPersons requestPersons, PersonsEntity personsEntity, boolean isUpdate){
        personsEntity.setNumDocument(requestPersons.getNumDocument());
        personsEntity.setEmail(requestPersons.getEmail());
        personsEntity.setTelephone(requestPersons.getTelephone());
        personsEntity.setStatus(Constants.STATUS_ACTIVE);
        personsEntity.setDocumentsTypeEntity(getDocumentsType(requestPersons));
        personsEntity.setEnterprisesEntity(getEnterprisesEntity(requestPersons));
        if(isUpdate){
            personsEntity.setUserModif(Constants.AUDIT_ADMIN);
            personsEntity.setDateModif(getTimestamp());
        }
        personsEntity.setUserCreate(Constants.AUDIT_ADMIN);
        personsEntity.setDateCreate(getTimestamp());

        return personsEntity;
    }

    private ResponseReniec getExecutionReniec(String numero) {
        String redisCache = redisService.getValueByKey(Constants.REDIS_KEY_INFO_REINIEC + numero);
        if (redisCache != null) {
            return Util.convertFromJson(redisCache, ResponseReniec.class);
        }
        String authorization = Constants.BEARER_PREFIX + tokenReniec;
        ResponseReniec reniec = reniecClient.getInfoReniec(numero, authorization);
        String redisData = Util.convertToJson(reniec);
        redisService.saveKeyValue(Constants.REDIS_KEY_INFO_REINIEC+ numero, redisData, Integer.valueOf(timeExpirationReniec));
        return reniec;
    }

    private DocumentsTypeEntity getDocumentsType(RequestPersons request) {
        DocumentsTypeEntity documentsType = new DocumentsTypeEntity();
        documentsType.setIdDocumentsType(request.getDocuments_type_id_documents_type());
        return documentsType;
    }

    private EnterprisesEntity getEnterprisesEntity(RequestPersons request) {
        EnterprisesEntity enterprisesEntity = new EnterprisesEntity();
        enterprisesEntity.setIdEnterprises(request.getEnterprises_id_enterprises());
        return enterprisesEntity;
    }

    private Timestamp getTimestamp() {
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }

}
