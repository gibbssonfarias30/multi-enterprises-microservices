package com.backdevfc.msmasterdata.domain.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnterprisesType extends Audit {
    private Long idEnterprisesType;
    private String descType;
    private String codType;
    private int status;

    public EnterprisesType(Long idEnterprisesType, String codType, String descType, int status, String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
        this.idEnterprisesType = idEnterprisesType;
        this.codType = codType;
        this.descType = descType;
        this.status = status;
        this.setUserCreate(userCreate);
        this.setDateCreate(dateCreate);
        this.setUserModif(userModif);
        this.setDateModif(dateModif);
    }
}
