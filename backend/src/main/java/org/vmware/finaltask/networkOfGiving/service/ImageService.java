package org.vmware.finaltask.networkOfGiving.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.vmware.finaltask.networkOfGiving.interfaces.IImageService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService implements IImageService {

    private static final String IMAGE_FOLDER = "src/main/resources/static/charityImages/";

    @Override
    public void saveImage(MultipartFile imageFile) throws IOException {
        byte[] bytes = imageFile.getBytes();

        Path imagePath = Paths.get(IMAGE_FOLDER + imageFile.getOriginalFilename());
        Files.write(imagePath, bytes);
    }
}
