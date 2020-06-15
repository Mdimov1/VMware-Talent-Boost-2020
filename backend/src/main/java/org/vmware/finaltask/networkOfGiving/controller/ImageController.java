package org.vmware.finaltask.networkOfGiving.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.vmware.finaltask.networkOfGiving.service.ImageService;

import java.io.IOException;

@RestController
@CrossOrigin("*")
public class ImageController {

    ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload/image")
    public void uploadImage(@RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        imageService.saveImage(imageFile);
    }
}
