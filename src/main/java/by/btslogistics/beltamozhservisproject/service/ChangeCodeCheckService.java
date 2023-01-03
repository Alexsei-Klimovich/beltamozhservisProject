package by.btslogistics.beltamozhservisproject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ChangeCodeCheckService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeCodeCheckService.class);

    public void findCodeChecks(File excelFile, File javaCode, File updatedFile) throws IOException {
        List<String> file = Files.readAllLines(Path.of(String.valueOf(javaCode)));
        Map<String, String> checks = ExcelService.oldAndNewChecksFromExcel(excelFile);
        for (Map.Entry<String, String> check: checks.entrySet()) {
            LOGGER.info(check.getKey() + " : " + check.getValue());
        }
        LOGGER.info("------------------------------------------- : checks for replace: " + checks.size());
        replaceCodeCheck(checks, file, updatedFile);
    }

    private void replaceCodeCheck(Map<String, String> checks, List<String> file, File updatedFile) {
        List<String> newFile = new ArrayList<>();
        for (Map.Entry<String, String> check : checks.entrySet()) {
            file.forEach(line -> {
                if (line.contains(check.getKey())) {
                    newFile.add(line.replace(line.split("\"")[1], check.getValue()));
                    LOGGER.info("find check for replace: " + check.getKey() + " replacing on: " + check.getValue());
                } else newFile.add(line);
            });
        }
        saveJavaFile(newFile, updatedFile);
    }

    //TODO CREATE NEW SERVICE FOR SAVING FILES
    private void saveJavaFile(List<String> javaFileList, File updatedFile) {
        LOGGER.info("staring save new file");
        try {
            File file = new File(String.valueOf(updatedFile));
            Files.write(Path.of(String.valueOf(updatedFile)), javaFileList);
            LOGGER.info("file saved!");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
