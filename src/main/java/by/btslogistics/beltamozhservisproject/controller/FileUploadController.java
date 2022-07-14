package by.btslogistics.beltamozhservisproject.controller;

import by.btslogistics.beltamozhservisproject.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;
    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        if (multipartFile == null) {
            System.out.println("File is NULL");
        } else {
            fileUploadService.fileUpload(multipartFile);
        }
        return String.format("File %s file uploaded", multipartFile.getOriginalFilename());
    }
}
