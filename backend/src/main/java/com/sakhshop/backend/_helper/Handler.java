package com.sakhshop.backend._helper;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Handler {

    private Handler() {
    }

    // Генерируем имя файла на основе хеша md5
    public static String generateFileName (String filePath) {


        byte[] data;
        try {
            data = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            return "";
        }
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(data);
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
        return new BigInteger(1, hash).toString(16);

    }



    // Получаем расширение на основе MimeType
    public static String getExtension(String ext) {

        return switch (ext) {
            case "image/jpeg" -> "jpg";
            case "image/png" -> "png";
            default -> "";
        };
    }


}
