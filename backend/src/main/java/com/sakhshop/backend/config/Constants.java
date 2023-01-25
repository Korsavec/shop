package com.sakhshop.backend.config;

import com.sakhshop.backend.models.payload.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Constants {

    private Constants() {
    }



    private static final String ENVIRONMENT_USER_HOM = "user.home";



    public static final String ENVIRONMENT_PATH_PASSPORT_LOGISTICS_COMPANY =
            System.getProperty(ENVIRONMENT_USER_HOM) + "/sakhshop/resources/passport/logistics/company";
    public static final String ENVIRONMENT_PATH_PASSPORT_LOGISTICS_PERSON =
            System.getProperty(ENVIRONMENT_USER_HOM) + "/sakhshop/resources/passport/logistics/person";
    public static final String ENVIRONMENT_PATH_PASSPORT_SELLER =
            System.getProperty(ENVIRONMENT_USER_HOM) + "/sakhshop/resources/passport/seller";




    public static final String ENVIRONMENT_PATH_TMP_PASSPORT_COMPANY =
            System.getProperty(ENVIRONMENT_USER_HOM) + "/sakhshop/tmp/passport/logistics/company";
    public static final String ENVIRONMENT_PATH_TMP_PASSPORT_PERSON =
            System.getProperty(ENVIRONMENT_USER_HOM) + "/sakhshop/tmp/passport/logistics/person";
    public static final String ENVIRONMENT_PATH_TMP_PASSPORT_SELLER =
            System.getProperty(ENVIRONMENT_USER_HOM) + "/sakhshop/tmp/passport/seller";






    public static final String SLASH = "/";
    public static final String DOT = ".";
    public static final String EXTENSION_JPG = "jpg";
    public static final String EXTENSION_PNG = "png";
    public static final String STATIC_OK = "OK";
    public static final String STATIC_NO = "no";
    public static final String STATIC_EMAIL = "email";

    public static ResponseEntity<MessageResponse> customMessageConfirm() {
        return new ResponseEntity<>(new MessageResponse(HttpStatus.NOT_FOUND.value(),
                STATIC_OK),
                HttpStatus.OK);
    }

}
