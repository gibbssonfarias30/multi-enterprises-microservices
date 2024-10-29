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
public class DocumentsType extends Audit {
    private Long idDocumentsType;
    private String descType;
    private String codType;
    private int status;

    public DocumentsType(Long idDocumentsType, String codType, String descType, int status, String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
        this.idDocumentsType = idDocumentsType;
        this.codType = codType;
        this.descType = descType;
        this.status = status;
        this.setUserCreate(userCreate);
        this.setDateCreate(dateCreate);
        this.setUserModif(userModif);
        this.setDateModif(dateModif);
    }
}
