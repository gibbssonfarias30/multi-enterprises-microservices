package com.backdevfc.msregister.aggregates.enums;

import lombok.Getter;

@Getter
public enum ETypeDocuments {

    DNI("01"),
    RUC("02");

    private final String value;

    ETypeDocuments(String value) {
        this.value = value;
    }
}
