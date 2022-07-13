package by.btslogistics.beltamozhservisproject.controller;

import by.btslogistics.beltamozhservisproject.exception.InvalidFileTypeException;
import by.btslogistics.beltamozhservisproject.parser.xsd.XsdParser;
import by.btslogistics.beltamozhservisproject.service.ExcelService;
import org.apache.commons.compress.utils.FileNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.btslogistics.beltamozhservisproject.service.ExcelService.excelParse;

@RestController
public class FileUploadController {
@Autowired
ExcelService excelParse;


    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        String fileName = FileNameUtils.getExtension(multipartFile.getOriginalFilename());
        List<String> splitedOriginalName =
                List.of(fileName);
        for (String l : splitedOriginalName) {
            System.out.println(l);
        }
        String fileType = splitedOriginalName.get(1);
        if (fileType.equals("xlsx")) {
            File newFile = File.createTempFile("data1-",".xlsx");
            multipartFile.transferTo(newFile);
            excelParse.saveParsedRows(excelParse(newFile)); // parsing xlsx file and saving to BD
            newFile.deleteOnExit();
        } else if (fileType.equals("xlm")||fileType.equals("xsd")) {
            File newFile = File.createTempFile("data-",".xsd");
            multipartFile.transferTo(newFile);
            XsdParser.parseXsd(newFile); // print xsd File to console
            newFile.deleteOnExit();
        } else {
            throw new InvalidFileTypeException();
        }
        return String.format("File %s file uploaded", multipartFile.getOriginalFilename());
    }
}
