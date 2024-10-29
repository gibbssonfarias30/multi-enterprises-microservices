package com.backdevfc.msregister.aggregates.model;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

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

    @Column(name = "user_delete", length = 45)
    private String userDelete;

    @Column(name = "date_delete")
    private Timestamp dateDelete;
}
