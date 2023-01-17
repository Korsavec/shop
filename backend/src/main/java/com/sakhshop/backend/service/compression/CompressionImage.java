
package com.sakhshop.backend.service.compression;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import static com.sakhshop.backend.config.Constants.*;

public class CompressionImage {

    private CompressionImage() {
    }

    // Сжимаем изображение
    // https://www.tutorialspoint.com/java_dip/image_compression_technique.htm
    // https://mkyong.com/java/convert-png-to-jpeg-image-file-in-java/

    public static boolean compressionAndConversion(String paths, String fileNameTmp, String extension) {



        Path sourcePath = Paths.get(paths + SLASH + fileNameTmp + "." + extension);

        String outputPath = paths + SLASH + fileNameTmp + DOT + EXTENSION_JPG;

        if (extension.equals(EXTENSION_PNG)) {

            ///////////// Конвертируем изображение png в jpg


            // Меняем расширение
            Path target = Paths.get(outputPath);

            BufferedImage originalImage;
            try {
                originalImage = ImageIO.read(sourcePath.toFile());
            } catch (IOException e) {
                return false;
            }

            // jpg needs BufferedImage.TYPE_INT_RGB
            // png needs BufferedImage.TYPE_INT_ARGB

            // create a blank, RGB, same width and height
            BufferedImage newBufferedImage = new BufferedImage(
                    originalImage.getWidth(),
                    originalImage.getHeight(),
                    BufferedImage.TYPE_INT_RGB);

            // Рисуем белый фон и помещаем на него исходное изображение.
            newBufferedImage.createGraphics().drawImage(originalImage,0,0, Color.WHITE,null);

            // Сохраняем изображение
            try {
                ImageIO.write(newBufferedImage, EXTENSION_JPG, target.toFile());


                Files.deleteIfExists(sourcePath);
            } catch (IOException e) {
                return false;
            }

        }






        ///////////// Сжимаем изображение


        File file = new File(outputPath);
        BufferedImage image;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            return false;
        }

        File compressedImageFile = new File(outputPath);

        try(OutputStream os = new FileOutputStream(compressedImageFile); ImageOutputStream ios = ImageIO.createImageOutputStream(os)) {

                Iterator<ImageWriter> writers =  ImageIO.getImageWritersByFormatName(EXTENSION_JPG);
                ImageWriter writer = writers.next();

                writer.setOutput(ios);

                ImageWriteParam param = writer.getDefaultWriteParam();

                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                param.setCompressionQuality(0.3f);
                writer.write(null, new IIOImage(image, null, null), param);

                writer.dispose();

                return true;

        } catch (IOException e) {
            return false;
        }

    }

}
