package com.backdevfc.msmasterdata.infrastructure.entity;


import com.backdevfc.msmasterdata.domain.model.DocumentsType;
import com.backdevfc.msmasterdata.infrastructure.entity.common.Audit;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Table(name = "documents_type")
@Entity
public class DocumentsTypeEntity extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documents_type")
    private Long idDocumentsType;

    @Column(name = "cod_type", length = 45, nullable = false)
    private String codType;

    @Column(name = "desc_type", length = 45, nullable = false)
    private String descType;

    @Column(name = "status", nullable = false)
    private int status;

    public DocumentsTypeEntity(Long idDocumentsType, String codType, String descType, int status, String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
        this.idDocumentsType = idDocumentsType;
        this.codType = codType;
        this.descType = descType;
        this.status = status;
        this.setUserCreate(userCreate);
        this.setDateCreate(dateCreate);
        this.setUserModif(userModif);
        this.setDateModif(dateModif);
    }


    public static DocumentsTypeEntity fromDomainModel(DocumentsType documentType) {
        return new DocumentsTypeEntity(documentType.getIdDocumentsType(), documentType.getCodType(),
                documentType.getDescType(), documentType.getStatus(), documentType.getUserCreate(),
                documentType.getDateCreate(), documentType.getUserModif(), documentType.getDateModif());
    }

    public DocumentsType toDomainModel() {
        return new DocumentsType(idDocumentsType, codType, descType, status, getUserCreate(), getDateCreate(), getUserModif(), getDateModif());
    }

}
