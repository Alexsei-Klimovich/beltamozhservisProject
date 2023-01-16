package by.btslogistics.beltamozhservisproject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class SaveFileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaveFileService.class);

    public void saveJavaFile(List<String> javaFileList, File updatedFile) {
        try {
            LOGGER.info("staring save new file");
            File file = new File(String.valueOf(updatedFile));
            Files.write(Path.of(String.valueOf(updatedFile)), javaFileList);
            LOGGER.info("file saved!");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
