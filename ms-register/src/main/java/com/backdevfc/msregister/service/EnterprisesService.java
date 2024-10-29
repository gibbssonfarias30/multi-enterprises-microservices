package com.backdevfc.msregister.service;

import com.backdevfc.msregister.aggregates.request.RequestEnterprises;
import com.backdevfc.msregister.aggregates.response.ResponseBase;

public interface EnterprisesService {
    ResponseBase createEnterprise(RequestEnterprises requestEnterprises);
    ResponseBase findOneEnterprise(String ruc);
    ResponseBase findAllEnterprises();
    ResponseBase updateEnterprise(Integer id, RequestEnterprises requestEnterprises);
    ResponseBase deleteEnterprise(Integer id);
}
