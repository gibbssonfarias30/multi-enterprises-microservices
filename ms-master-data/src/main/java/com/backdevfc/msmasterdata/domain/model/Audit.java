package com.backdevfc.msmasterdata.domain.model;


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

    private String userCreate;
    private Timestamp dateCreate;
    private String userModif;
    private Timestamp dateModif;
}
