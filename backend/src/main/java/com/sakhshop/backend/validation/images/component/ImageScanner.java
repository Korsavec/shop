package com.sakhshop.backend.validation.images.component;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.io.IOException;
import java.io.InputStream;

public final class ImageScanner {

	private ImageScanner() {}

	public static Result scan(InputStream imgInputStream, String extension) {

		Result result = new Result();

		ImageReader imgReader = ImageIO.getImageReadersByFormatName(extension).next();

		try (imgInputStream) {

			// Удалить все объекты Listener
			imgReader.removeAllIIOReadProgressListeners();
			imgReader.removeAllIIOReadUpdateListeners();
			imgReader.removeAllIIOReadWarningListeners();

			imgReader.addIIOReadWarningListener((final ImageReader source, final String warning) -> {
				result.getMessagesSb().append(warning).append("\n");
				result.setOk(false);
				result.setResultType(Type.WARNING);
			});

			imgReader.setInput(ImageIO.createImageInputStream(imgInputStream));

			int imgCount = imgReader.getNumImages(true);
			for (int i = 0; i < imgCount; i++) {
				imgReader.read(i);
			}

		} catch (NegativeArraySizeException ex) {
			result.getMessagesSb().append("Internal decoder error 1");
			result.getMessagesSb().append(ex.getMessage()).append("\n");
			result.setOk(false);
			result.setResultType(Type.ERROR);
		} catch (ArrayIndexOutOfBoundsException ex) {
			result.getMessagesSb().append("Internal decoder error 2");
			result.getMessagesSb().append(ex.getMessage()).append("\n");
			result.setOk(false);
			result.setResultType(Type.ERROR);
		} catch (IOException ex) {
			result.getMessagesSb().append(ex.getMessage()).append("\n");
			result.setOk(false);
			result.setResultType(Type.ERROR);
		} catch (Exception e) {
			result.getMessagesSb().append(e.getMessage()).append("\n");
			result.setOk(false);
			result.setResultType(Type.UNEXPECTED_ERROR);
		} finally {
			imgReader.dispose();
		}

		return result;
	}
}
