package com.sakhshop.backend.config;

public class Constants {

    private Constants() {
    }

    private static final String ENVIRONMENT_USER_HOM = "user.home";

    public static final String FILE_SYSTEM_PATH_RESOURCES_PASSPORT = System.getProperty(ENVIRONMENT_USER_HOM) + "/sakhshop/resources/passport";
    public static final String FILE_SYSTEM_PATH_TMP_PASSPORT = System.getProperty(ENVIRONMENT_USER_HOM) + "/sakhshop/tmp/passport";
    public static final String FILE_SYSTEM_PATH_TMP_TEST = System.getProperty(ENVIRONMENT_USER_HOM) + "/sakhshop/tmp/test";

}
