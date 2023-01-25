package com.sakhshop.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

import static com.sakhshop.backend.config.Constants.*;

@SpringBootApplication
public class ShopApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(ShopApplication.class, args);

        if(new File(ENVIRONMENT_PATH_PASSPORT_LOGISTICS_COMPANY).mkdirs()) {
            LOGGER.info("Create directory ENVIRONMENT_PATH_PASSPORT_LOGISTICS_COMPANY ok");
        } else {
            LOGGER.info("Create directory ENVIRONMENT_PATH_PASSPORT_LOGISTICS_COMPANY Exists");
        }


        if(new File(ENVIRONMENT_PATH_PASSPORT_LOGISTICS_PERSON).mkdirs()) {
            LOGGER.info("Create directory ENVIRONMENT_PATH_PASSPORT_LOGISTICS_PERSON ok");
        } else {
            LOGGER.info("Create directory ENVIRONMENT_PATH_PASSPORT_LOGISTICS_PERSON Exists");
        }

        if(new File(ENVIRONMENT_PATH_PASSPORT_SELLER).mkdirs()) {
            LOGGER.info("Create directory ENVIRONMENT_PATH_PASSPORT_SELLER ok");
        } else {
            LOGGER.info("Create directory ENVIRONMENT_PATH_PASSPORT_SELLER Exists");
        }




        if(new File(ENVIRONMENT_PATH_TMP_PASSPORT_COMPANY).mkdirs()) {
            LOGGER.info("Create directory ENVIRONMENT_PATH_TMP_PASSPORT_COMPANY ok");
        } else {
            LOGGER.info("Create directory ENVIRONMENT_PATH_TMP_PASSPORT_COMPANY Exists");
        }

        if(new File(ENVIRONMENT_PATH_TMP_PASSPORT_PERSON).mkdirs()) {
            LOGGER.info("Create directory ENVIRONMENT_PATH_TMP_PASSPORT_PERSON ok");
        } else {
            LOGGER.info("Create directory ENVIRONMENT_PATH_TMP_PASSPORT_PERSON Exists");
        }

        if(new File(ENVIRONMENT_PATH_TMP_PASSPORT_SELLER).mkdirs()) {
            LOGGER.info("Create directory ENVIRONMENT_PATH_TMP_PASSPORT_SELLER ok");
        } else {
            LOGGER.info("Create directory ENVIRONMENT_PATH_TMP_PASSPORT_SELLER Exists");
        }

    }


}