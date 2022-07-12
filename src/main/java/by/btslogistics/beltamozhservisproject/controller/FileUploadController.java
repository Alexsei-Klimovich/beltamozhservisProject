package by.btslogistics.beltamozhservisproject.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file")MultipartFile multipartFile){
        System.out.println(multipartFile.getOriginalFilename());
        return String.format("File %s file uploaded", multipartFile.getOriginalFilename());
    }
}
