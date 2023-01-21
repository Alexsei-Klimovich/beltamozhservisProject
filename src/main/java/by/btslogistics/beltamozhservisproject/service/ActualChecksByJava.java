package by.btslogistics.beltamozhservisproject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ActualChecksByJava {

    @Autowired
    SaveFileService saveFileService;

    private static Logger LOGGER = LoggerFactory.getLogger(ActualChecksByJava.class);

    private int countChecks = 0;

    private int existsInDirectory = 0;

    // метод достает проверки из java кода и ищет их по справочнику
    public void comparingChecksByJava(File excelFile, File javaFile, File updatedFile) throws IOException {
        countChecks = 0;
        existsInDirectory = 0;
        LOGGER.info("starting parse and comparing");
        List<String> parsedChecksFromDirectory = ExcelService.checksFromExcel(excelFile);
        List<String> checksNotExists = new ArrayList<>();
        List<String> parsedFile = new ArrayList<>();
        List<String> javaFileList = Files.readAllLines(Paths.get(String.valueOf(javaFile)));

        try {
            parsedFile = parseChecksFromJava(javaFileList);
            parsedFile.forEach(x -> {
                if (parsedChecksFromDirectory.stream().noneMatch(x::contains))
                    checksNotExists.add(x);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        existsInDirectory = checksNotExists.size();
        LOGGER.info("starting save checks into DB");
        saveToNotExist(checksNotExists);
        LOGGER.info("staring deleting cases from java source code");
        deleteCaseFromJava(checksNotExists, javaFileList, updatedFile);
    }

    //parsing java switch for checks
    private List<String> parseChecksFromJava(List<String> javaFile) {

        List<String> parsedChecks = new ArrayList<>();
        javaFile.forEach(x -> {
            if (x.contains("case \"")) {
                parsedChecks.add(x.split("\"")[1]);
                countChecks++;
            }
        });
        return parsedChecks;
    }

    private void saveToNotExist(List<String> checksNotExists) {
        if (checksNotExists.size() != 0) {
            LOGGER.info("count checks in java code: " + countChecks);
            LOGGER.info("count checks which not exists in directory: " + existsInDirectory);
            checksNotExists.forEach(LOGGER::info);
        }
    }

    //deleting cases which not exist in directory
    private void deleteCaseFromJava(List<String> checksNotExists, List<String> javaFileList, File updatedFile) {
        List<Integer> numRowsDelete = new ArrayList<>();
        LOGGER.info("starting delete cases");
        AtomicBoolean flag = new AtomicBoolean(false);
        checksNotExists.forEach(check -> {
            for (int j = 0; j < javaFileList.size()-1; j++) {
                if (javaFileList.get(j).contains(check)) {
                    LOGGER.info("find not exist case: " + check);
                    numRowsDelete.add(j);
                    j++;
                    while(!javaFileList.get(j).contains("case \"") || flag.get()) {
                        if (javaFileList.get(j).contains("default:"))
                            flag.set(true);
                        numRowsDelete.add(j);
                        j++;
                    }
                    flag.set(false);
                }
            }
        });
        for (int i = numRowsDelete.size() - 1; i >= 0; i--) {
            int delete = numRowsDelete.get(i);
            javaFileList.remove(delete);
        }
        saveFileService.saveJavaFile(javaFileList, updatedFile);
    }

}
