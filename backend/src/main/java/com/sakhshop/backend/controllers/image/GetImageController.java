package com.sakhshop.backend.controllers.image;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.sakhshop.backend.config.Constants.*;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@RestController
@RequestMapping("/resources/Guard/image/passport")
public class GetImageController {


    @ResponseBody
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/seller", produces = IMAGE_JPEG_VALUE)
    public byte[] getPassportPictureSeller() throws IOException {
        return Files.readAllBytes(Paths.get(ENVIRONMENT_PATH_PASSPORT_SELLER + "/6776543345.jpg"));
    }


    @ResponseBody
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/logistics/company", produces = IMAGE_JPEG_VALUE)
    public byte[] getPassportPictureLogisticsCompany() throws IOException {
        return Files.readAllBytes(Paths.get(ENVIRONMENT_PATH_PASSPORT_LOGISTICS_COMPANY + "/8746367655.jpg"));
    }


    @ResponseBody
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/logistics/person", produces = IMAGE_JPEG_VALUE)
    public byte[] getPassportPictureLogisticsPerson() throws IOException {
        return Files.readAllBytes(Paths.get(ENVIRONMENT_PATH_PASSPORT_LOGISTICS_PERSON + "/5678987654.jpg"));
    }


}
