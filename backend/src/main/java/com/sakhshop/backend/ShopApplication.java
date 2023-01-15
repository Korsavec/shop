package com.sakhshop.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

import static com.sakhshop.backend.config.Constants.*;

@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {

        SpringApplication.run(ShopApplication.class, args);

        if(new File(FILE_SYSTEM_PATH_RESOURCES_PASSPORT).mkdirs()) {
            System.out.println("Create directory FILE_SYSTEM_PATH_RESOURCES ok");
        } else {
            System.err.println("Create directory FILE_SYSTEM_PATH_RESOURCES Exists");
        }

        if(new File(FILE_SYSTEM_PATH_TMP_PASSPORT).mkdirs()) {
            System.out.println("Create directory FILE_SYSTEM_PATH_TMP_PASSPORT ok");
        } else {
            System.err.println("Create directory FILE_SYSTEM_PATH_TMP_PASSPORT Exists");
        }

        if(new File(FILE_SYSTEM_PATH_TMP_TEST).mkdirs()) {
            System.out.println("Create directory FILE_SYSTEM_PATH_TMP_TEST ok");
        } else {
            System.err.println("Create directory FILE_SYSTEM_PATH_TMP_TEST Exists");
        }

    }


}