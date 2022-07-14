package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.controller.FileUploadController;
import by.btslogistics.beltamozhservisproject.exception.InvalidFileTypeException;
import by.btslogistics.beltamozhservisproject.parser.xsd.XsdParser;
import org.apache.commons.compress.utils.FileNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static by.btslogistics.beltamozhservisproject.service.ExcelService.excelParse;

@Service
public class FileUploadService {
    @Autowired
    ExcelService excelParse;

    public String fileUpload(MultipartFile multipartFile) throws IOException {
        String fileName = FileNameUtils.getExtension(multipartFile.getOriginalFilename());
        List<String> splitedOriginalName =
                List.of(fileName);
        for (String l : splitedOriginalName) {
            System.out.println(l);
        }
        String fileType = splitedOriginalName.get(0);
        if (fileType.equals("xlsx")) {
            File newFile = File.createTempFile("data-",".xlsx");
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
