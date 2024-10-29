package com.backdevfc.msmasterdata.infrastructure.config;

import com.backdevfc.msmasterdata.application.service.DocumentsTypeService;
import com.backdevfc.msmasterdata.application.service.EnterprisesTypeService;
import com.backdevfc.msmasterdata.application.usecase.DocumentsTypeServiceImpl;
import com.backdevfc.msmasterdata.application.usecase.EnterprisesTypeServiceImpl;
import com.backdevfc.msmasterdata.domain.port.out.DocumentsTypeOut;
import com.backdevfc.msmasterdata.domain.port.out.EnterprisesTypeOut;
import com.backdevfc.msmasterdata.infrastructure.repository.adapter.DocumentsTypeRepositoryAdapter;
import com.backdevfc.msmasterdata.infrastructure.repository.adapter.EnterprisesTypeRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public DocumentsTypeService documentsTypeService(DocumentsTypeOut documentsTypeOut) {
        return new DocumentsTypeService(new DocumentsTypeServiceImpl(documentsTypeOut));
    }

    @Bean
    public DocumentsTypeOut documentsTypeOut(DocumentsTypeRepositoryAdapter documentsTypeRepositoryAdapter) {
        return documentsTypeRepositoryAdapter;
    }

    @Bean
    public EnterprisesTypeService enterprisesTypeService(EnterprisesTypeOut enterprisesTypeOut) {
        return new EnterprisesTypeService(new EnterprisesTypeServiceImpl(enterprisesTypeOut));
    }

    @Bean
    public EnterprisesTypeOut enterprisesTypeOut(EnterprisesTypeRepositoryAdapter enterprisesTypeRepositoryAdapter) {
        return enterprisesTypeRepositoryAdapter;
    }
}
