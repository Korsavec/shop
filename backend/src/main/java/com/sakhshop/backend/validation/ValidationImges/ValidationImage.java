package com.sakhshop.backend.validation.ValidationImges;

import com.sakhshop.backend.validation.ValidationImges.component.Result;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.sakhshop.backend.validation.ValidationImges.component.ImageScanner.scan;

public class ValidationImage {

    public static boolean checkingImage (Path filePath, String extension) {

        try {

            Result result = scan(Files.newInputStream(filePath), extension);

            return result.getOk();

        } catch (IOException e) {

            return false;

        }

    }

}
