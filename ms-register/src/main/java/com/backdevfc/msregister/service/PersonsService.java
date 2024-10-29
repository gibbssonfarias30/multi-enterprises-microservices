package com.backdevfc.msregister.service;

import com.backdevfc.msregister.aggregates.request.RequestPersons;
import com.backdevfc.msregister.aggregates.response.ResponseBase;

public interface PersonsService {

    ResponseBase getInfoReniec(String numero);
    ResponseBase createPersons(RequestPersons requestPersons);
    ResponseBase findOnePerson(Integer id);
    ResponseBase findAll();
    ResponseBase updatePersons(Integer id, RequestPersons requestPersons);
}

