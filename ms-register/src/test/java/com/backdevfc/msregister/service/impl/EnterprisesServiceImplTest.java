package com.backdevfc.msregister.service.impl;

import com.backdevfc.msregister.aggregates.constants.Constants;
import com.backdevfc.msregister.aggregates.request.RequestEnterprises;
import com.backdevfc.msregister.aggregates.response.ResponseBase;
import com.backdevfc.msregister.aggregates.response.ResponseSunat;
import com.backdevfc.msregister.client.SunatClient;
import com.backdevfc.msregister.config.RedisService;
import com.backdevfc.msregister.entity.DocumentsTypeEntity;
import com.backdevfc.msregister.entity.EnterprisesEntity;
import com.backdevfc.msregister.entity.EnterprisesTypeEntity;
import com.backdevfc.msregister.repository.DocumentsTypeRepository;
import com.backdevfc.msregister.repository.EnterprisesRepository;
import com.backdevfc.msregister.repository.EnterprisesTypeRepository;
import com.backdevfc.msregister.util.EnterprisesValidations;
import com.backdevfc.msregister.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;


class EnterprisesServiceImplTest {

    @Mock
    EnterprisesRepository enterprisesRepository;

    @Mock
    EnterprisesValidations enterprisesValidations;

    @Mock
    DocumentsTypeRepository documentsTypeRepository;

    @Mock
    RedisService redisService;

    @Mock
    EnterprisesTypeRepository enterprisesTypeRepository;

    @Mock
    Util util;

    @Mock
    SunatClient sunatClient;

    @InjectMocks
    EnterprisesServiceImpl enterprisesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        enterprisesService = new EnterprisesServiceImpl(sunatClient, enterprisesRepository,documentsTypeRepository, redisService, enterprisesValidations);
    }

    @Test
    void getInfoSunatSucceed() {
        String numero = "20602356459";
        ResponseSunat responseSunat = new ResponseSunat(
                "POLLOS GORDOS S.A.C.",
                "6",
                "20602356459",
                "ACTIVO",
                "HABIDO",
                "AV. PETIT THOUARS NRO 9988 URB. VALLECITO ",
                "123456",
                "AV.",
                "PETIT THOUARS",
                "URB.",
                "VALLECITO",
                "9988",
                "-",
                "-",
                "-",
                "-",
                "-",
                "MIRAFLORES",
                "AREQUIPA",
                "AREQUIPA",
                true
        );
        ResponseBase responseBaseExpected = new ResponseBase(Constants.CODE_SUCCESS, Constants.MESS_SUCCESS, Optional.of(responseSunat));

        Mockito.when(sunatClient.getInfoSunat(anyString(), anyString())).thenReturn(responseSunat);

        ResponseSunat sunat = enterprisesService.getExecutionSunat(numero);
        ResponseBase responseBase = new ResponseBase(Constants.CODE_SUCCESS, Constants.MESS_SUCCESS, Optional.of(sunat));

        assertEquals(responseBase.getCode(), responseBaseExpected.getCode());
        assertEquals(responseBase.getMessage(), responseBaseExpected.getMessage());
        assertEquals(responseBase.getData(), responseBaseExpected.getData());
    }
    @Test
    void getInfoSunatError() {
        String numero = "XDCCCCCC";
        Mockito.when(sunatClient.getInfoSunat(anyString(), anyString())).thenReturn(null);
        ResponseSunat sunat = enterprisesService.getExecutionSunat(numero);
        assertNull(sunat);
    }

    @Test
    void createEnterpriseSucceed() {
        boolean validateEnterprise = true;
        RequestEnterprises requestEnterprises = new RequestEnterprises(
                "20602356459",
                "Los Pollos Gordos",
                "POLLOS GORDOS S.A.C.",
                2,
                3
        );
        ResponseSunat responseSunat = new ResponseSunat(
                "POLLOS GORDOS S.A.C.",
                "6",
                "20602356459",
                "ACTIVO",
                "HABIDO",
                "AV. PETIT THOUARS NRO 9988 URB. VALLECITO ",
                "123456",
                "AV.",
                "PETIT THOUARS",
                "URB.",
                "VALLECITO",
                "9988",
                "-",
                "-",
                "-",
                "-",
                "-",
                "MIRAFLORES",
                "AREQUIPA",
                "AREQUIPA",
                true
        );
        EnterprisesTypeEntity enterprisesTypeEntity = new EnterprisesTypeEntity(
                2,
                "02",
                "SAC",
                1
        );
        DocumentsTypeEntity documentsTypeEntity = new DocumentsTypeEntity(
                3,
                "06",
                "RUC",
                1
        );
        EnterprisesEntity enterprisesEntity = new EnterprisesEntity(
                1,
                "20602356459",
                "Los Pollos Gordos",
                "POLLOS GORDOS S.A.C.",
                1,
                enterprisesTypeEntity,
                documentsTypeEntity
        );
        ResponseBase responseBaseExpected = new ResponseBase(Constants.CODE_SUCCESS, Constants.MESS_SUCCESS, Optional.of(enterprisesEntity));

        EnterprisesServiceImpl spy = Mockito.spy(enterprisesService);

        Mockito.when(enterprisesValidations.validateInput(Mockito.any(RequestEnterprises.class))).thenReturn(validateEnterprise);
        Mockito.doReturn(responseSunat).when(spy).getExecutionSunat(anyString());
        Mockito.when(enterprisesTypeRepository.findById(anyInt())).thenReturn(Optional.of(enterprisesTypeEntity));
        Mockito.when(enterprisesRepository.save(Mockito.any(EnterprisesEntity.class))).thenReturn(enterprisesEntity);
        Mockito.when(documentsTypeRepository.findById(anyInt())).thenReturn(Optional.of(documentsTypeEntity));
        ResponseBase responseBase = spy.createEnterprise(requestEnterprises);
        assertEquals(responseBase.getCode(), responseBaseExpected.getCode());
        assertEquals(responseBase.getMessage(), responseBaseExpected.getMessage());
    }


}