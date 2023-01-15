package com.sakhshop.backend.controllers.image;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.sakhshop.backend.config.Constants.FILE_SYSTEM_PATH_RESOURCES_PASSPORT;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@RestController
@RequestMapping("/resources/ResourcesGuard/passport")
public class GetImageController implements Serializable {


    @Serial
    private static final long serialVersionUID = 2024311313893164439L;

    @ResponseBody
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/image", produces = IMAGE_JPEG_VALUE)
    public byte[] getPassportPicture() throws IOException {
        return Files.readAllBytes(Paths.get(FILE_SYSTEM_PATH_RESOURCES_PASSPORT + "/image.jpg"));
    }


}
