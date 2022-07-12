package by.btslogistics.beltamozhservisproject.controller;

import by.btslogistics.beltamozhservisproject.parser.xsd.XsdParser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {



    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        File newFile = File.createTempFile("data-",".xsd");
        multipartFile.transferTo(newFile);
        XsdParser.parseXsd(newFile); //print xsd File to console
        newFile.deleteOnExit();
        return String.format("File %s file uploaded", multipartFile.getOriginalFilename());
    }
}
