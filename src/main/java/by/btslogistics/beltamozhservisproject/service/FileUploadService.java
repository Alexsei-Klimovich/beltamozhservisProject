package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.exception.InvalidFileTypeException;
import org.apache.commons.compress.utils.FileNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileUploadService {
    @Autowired
    ExcelService excelParse;

    @Autowired
    XmlService xmlService;
    private List<String> splitedOriginalName = new ArrayList<>();
    private String destenationPath = "uploadsFiles";
    private Path path = Path.of(destenationPath);
    private String fileExtension;
    private String fileName;
    private String filePath;
    public String fileUpload(MultipartFile multipartFile) throws IOException, ParserConfigurationException, SAXException {
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        System.out.println(multipartFile.getOriginalFilename());
        fileName = multipartFile.getOriginalFilename();
        filePath = destenationPath + "\\" + fileName;
        fileExtension = FileNameUtils.getExtension(multipartFile.getOriginalFilename()); // получаем расширение файла
        splitedOriginalName.add(fileExtension);
        System.out.println(splitedOriginalName.get(splitedOriginalName.lastIndexOf(fileExtension)));
        if (fileExtension.equals("xlsx") || fileExtension.equals("xls")) { // проверка на тип файла
            multipartFile.transferTo(new File(filePath));
//          excelParse.excelParse(new File(filePath));
        } else if (fileExtension.equals("xsd")) {
            multipartFile.transferTo(new File(filePath));
//          xmlService.saveDocumentInfo(new File(filePath));
        } else {
          throw new InvalidFileTypeException();
        }
        return "File uploaded";
    }
}
