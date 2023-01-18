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

        if(new File(FILE_SYSTEM_PATH_RESOURCES_PASSPORT).mkdirs()) {
            LOGGER.info("Create directory FILE_SYSTEM_PATH_RESOURCES ok");
        } else {
            LOGGER.info("Create directory FILE_SYSTEM_PATH_RESOURCES Exists");
        }

        if(new File(FILE_SYSTEM_PATH_TMP_PASSPORT).mkdirs()) {
            LOGGER.info("Create directory FILE_SYSTEM_PATH_TMP_PASSPORT ok");
        } else {
            LOGGER.info("Create directory FILE_SYSTEM_PATH_TMP_PASSPORT Exists");
        }

        if(new File(FILE_SYSTEM_PATH_TMP_TEST).mkdirs()) {
            LOGGER.info("Create directory FILE_SYSTEM_PATH_TMP_TEST ok");
        } else {
            LOGGER.info("Create directory FILE_SYSTEM_PATH_TMP_TEST Exists");
        }

    }


}