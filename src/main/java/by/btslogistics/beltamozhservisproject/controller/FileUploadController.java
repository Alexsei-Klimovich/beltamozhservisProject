package by.btslogistics.beltamozhservisproject.controller;

import by.btslogistics.beltamozhservisproject.exception.InvalidFileTypeException;
import by.btslogistics.beltamozhservisproject.parser.xsd.XsdParser;
import by.btslogistics.beltamozhservisproject.service.ExcelService;
import by.btslogistics.beltamozhservisproject.service.FileUploadService;
import org.apache.commons.compress.utils.FileNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static by.btslogistics.beltamozhservisproject.service.ExcelService.excelParse;

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
