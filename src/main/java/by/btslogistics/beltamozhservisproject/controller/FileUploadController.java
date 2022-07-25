package by.btslogistics.beltamozhservisproject.controller;
import by.btslogistics.beltamozhservisproject.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
@RestController
public class FileUploadController {
    @Autowired
    FileUploadService fileUploadService;
    private byte[] content = null;
    private MultipartFile uploadedFile;
    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file")MultipartFile[] multipartFile) throws IOException, ParserConfigurationException, SAXException {
        for (int i = 0; i < multipartFile.length; i++) {
            if (multipartFile == null) {
                System.out.println("File is NULL");
            } else {
                try {
                    content = multipartFile[i].getBytes();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                uploadedFile = new MockMultipartFile
                        (multipartFile[i].getOriginalFilename(),
                                multipartFile[i].getOriginalFilename(),
                                multipartFile[i].getContentType(),
                                content);
                fileUploadService.fileUpload(uploadedFile);
                uploadedFile = null;
            }
        }
        return "File uploaded";
    }
}
