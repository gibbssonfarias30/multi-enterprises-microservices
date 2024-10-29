package com.backdevfc.msregister.aggregates.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RequestEnterprises {

    private String numDocument;
    private String businessName;
    private String tradeName;
    private int enterprisesTypeEntity;
    private int documentsTypeEntity;
}
