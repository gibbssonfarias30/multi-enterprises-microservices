package com.backdevfc.msregister.aggregates.constants;

public class Constants {

    private Constants() {}

    public static final Integer CODE_SUCCESS = 200;
    public static final Integer CODE_ERROR = 500;
    public static final Integer CODE_ERROR_DATA_INPUT=1002;
    public static final Integer CODE_ERROR_DATA_NOT=1004;

    public static final String MESS_SUCCESS = "Success";
    public static final String MESS_ERROR = "Error en la ejecuto";
    public static final String MESS_ERROR_DATA_NOT_VALID = "Error durante las validaciones de los datos";
    public static final String MESS_NON_DATA = "No existe datos para ID";
    public static final String MESS_ZERO_ROWS = "No existe registros en BD";
    public static final String MESS_ERROR_NOT_UPDATE = "Error: No se ejecuto la actualizacion, Empresa no existe";
    public static final String MESS_ERROR_NOT_UPDATE_PERSON = "Error: No se ejecuto la actualizacion, Person no existe" ;
    public static final String MESS_NON_DATA_RENIEC= "No existe registros en el API de RENIEC";
    public static final String MESS_NON_DATA_SUNAT="No existe Registros en el API de SUNAT";

    public static final Integer LENGTH_RUC = 11;
    public static final Integer LENGTH_DNI = 8;

    public static final Integer STATUS_ACTIVE = 1;
    public static final Integer STATUS_INACTIVE = 0;

    public static final String AUDIT_ADMIN="ADMIN";

    public static final String BEARER_PREFIX = "Bearer ";

    public static final String REDIS_KEY_INFO_REINIEC="MS:REGISTER:INFORENIEC:";
    public static final String REDIS_KEY_INFO_SUNAT="MS:REGISTER:INFOSUNAT:";
    public static final String REDIS_KEY_INFO_SUNAT_CLIENT="API:SUNAT:";
    public static final String COD_TYPE_RUC = "06";

}


