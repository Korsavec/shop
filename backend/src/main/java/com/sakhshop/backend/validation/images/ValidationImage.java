package com.sakhshop.backend.validation.images;

import com.sakhshop.backend.validation.images.component.Result;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.sakhshop.backend.validation.images.component.ImageScanner.scan;

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
