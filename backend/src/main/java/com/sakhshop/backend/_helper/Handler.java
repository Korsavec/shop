package com.sakhshop.backend._helper;

import org.apache.tika.Tika;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
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



    public static boolean clearExif (Path filePath, String extension) {

        // Затираем все метаданные путём перезаписи.
        try (InputStream inputStream = Files.newInputStream(filePath)) {

            BufferedImage image = ImageIO.read(inputStream);

            try(OutputStream outputStream = Files.newOutputStream(filePath)) {

                ImageIO.write(image, extension, outputStream);

                return true;

            } catch (IOException e) {
                return false;
            }

        } catch (IOException e) {
            return false;
        }
    }



    // Получаем расширение на основе MimeType
    public static String getExtension(String ext) {

        return switch (ext) {
            case "image/jpeg" -> "jpg";
            case "image/png" -> "png";
            default -> "";
        };
    }



    // Получение ImageMimeType - image/jpeg
    private static final Tika TIKA = new Tika();
    public static String getImageMimeType(File src) {

        try (FileInputStream fis = new FileInputStream(src)) {

            return TIKA.detect(fis, src.getName());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
