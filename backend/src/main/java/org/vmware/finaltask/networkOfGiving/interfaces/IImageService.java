package org.vmware.finaltask.networkOfGiving.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IImageService {

    void saveImage(MultipartFile imageFile) throws IOException;
}
