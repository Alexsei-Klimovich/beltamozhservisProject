package by.btslogistics.beltamozhservisproject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ChangeCodeCheckService {

    @Autowired
    SaveFileService saveFileService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeCodeCheckService.class);

    public void findCodeChecks(File excelFile, File javaCode, File updatedFile) throws IOException {
        String str = "-";
        int count = 0;
        List<String> file = Files.readAllLines(Path.of(String.valueOf(javaCode)));
        Map<String, String> checks = ExcelService.oldAndNewChecksFromExcel(excelFile);
        for (Map.Entry<String, String> check: checks.entrySet()) {
            LOGGER.info(check.getKey() + " : " + check.getValue());
        }
        for (Map.Entry<String, String> check : checks.entrySet()) {
            if (!check.getKey().equals(check.getValue()))
                count++;
        }
        LOGGER.info(str.repeat(35) + " : checks for replace: " + checks.size());
        LOGGER.info(str.repeat(35) + " : same code_checks: " + count  + "\n");
        count = 0;
        replaceCodeCheck(checks, file, updatedFile);
    }

    private void replaceCodeCheck(Map<String, String> checks, List<String> file, File updatedFile) {
        List<String> newFile = new ArrayList<>();
        AtomicBoolean fl = new AtomicBoolean(true);
        String k = null;
        String v = null;
        for (String line : file) {
            for (Map.Entry<String, String> check : checks.entrySet()) {
                if (line.contains(check.getKey())) {
                    newFile.add(line.replace(line.split("\"")[1], check.getValue()));
                    fl.set(false);
                    LOGGER.info("find check for replace: " + check.getKey() + " replacing on: " + check.getValue());
                    k = check.getKey();
                    v = check.getValue();
                }
            }
            if (k != null && v != null)
                checks.remove(k, v);
            if (fl.getAndSet(true))
                newFile.add(line);
        }
        saveFileService.saveJavaFile(newFile, updatedFile);
    }

}
