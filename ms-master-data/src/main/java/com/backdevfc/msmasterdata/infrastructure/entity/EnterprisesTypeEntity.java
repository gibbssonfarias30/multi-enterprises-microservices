package com.backdevfc.msmasterdata.infrastructure.entity;

import com.backdevfc.msmasterdata.domain.model.EnterprisesType;
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
@Table(name = "enterprises_type")
@Entity
public class EnterprisesTypeEntity extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_enterprises_type")
    private Long idEnterprisesType;

    @Column(name = "cod_type", length = 45, nullable = false)
    private String codType;

    @Column(name = "desc_type", length = 45, nullable = false)
    private String descType;

    @Column(name = "status", nullable = false)
    private int status;

    public EnterprisesTypeEntity(Long idEnterprisesType, String codType, String descType, int status, String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif) {
        this.idEnterprisesType = idEnterprisesType;
        this.codType = codType;
        this.descType = descType;
        this.status = status;
        this.setUserCreate(userCreate);
        this.setDateCreate(dateCreate);
        this.setUserModif(userModif);
        this.setDateModif(dateModif);
    }


    public static EnterprisesTypeEntity fromDomainModel(EnterprisesType enterprisesType) {
        return new EnterprisesTypeEntity(enterprisesType.getIdEnterprisesType(), enterprisesType.getCodType(), 
                enterprisesType.getDescType(), enterprisesType.getStatus(), enterprisesType.getUserCreate(),
                enterprisesType.getDateCreate(), enterprisesType.getUserModif(), enterprisesType.getDateModif());
    }

    public EnterprisesType toDomainModel() {
        return new EnterprisesType(idEnterprisesType, codType, descType, status, getUserCreate() ,getDateCreate(), getUserModif(), getDateModif());
    }
}
