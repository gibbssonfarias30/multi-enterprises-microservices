package com.backdevfc.msmasterdata.infrastructure.entity.common;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class Audit {

    @Column(name = "user_create",length = 45)
    private String userCreate;

    @Column(name = "date_create")
    private Timestamp dateCreate;

    @Column(name = "user_modif", length = 45)
    private String userModif;

    @Column(name = "date_modif")
    private Timestamp dateModif;

}
