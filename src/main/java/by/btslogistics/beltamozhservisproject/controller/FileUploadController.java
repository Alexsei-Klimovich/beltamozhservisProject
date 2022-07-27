package by.btslogistics.beltamozhservisproject.controller;
import by.btslogistics.beltamozhservisproject.service.FileUploadService;
import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.aspectj.util.FileUtil.listFiles;

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

    @GetMapping("/getFilesNames")
    public List<String> getFilesNames() throws IOException {
        List<String> listFilesNames = new ArrayList<>();
        File folder = new File("uploadsFiles\\");
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                listFiles(file);
            } else {
                String fileExtension = FileNameUtils.getExtension(String.valueOf(file));
                if (fileExtension.equals("xlsx") || fileExtension.equals("xls")) {
                    listFilesNames.add(FilenameUtils.getName(String.valueOf(file)));
                }
            }
        }
        return listFilesNames;
    }
}
