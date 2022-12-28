package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.model.ChecksNotExistsModel;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActualByDirectoryService {

    public void comparingChecksByDirectory(File excelFile, File javaFile){
        List<String> parsedChecksFromDirectory = ExcelService.checksFromExcel(excelFile);
        List<String> checksNotExists = new ArrayList<>();

        try {
            List<String> file = Files.readAllLines(Paths.get(String.valueOf(javaFile)));
            parsedChecksFromDirectory.forEach(x -> {
                if (file.stream().noneMatch(line -> line.contains(x)))
                    checksNotExists.add(x);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (checksNotExists.size() != 0) {
            saveToNotExist(checksNotExists);
        }
    }

    private void saveToNotExist(List<String> checksNotExists) {
        ChecksNotExistsModel check = new ChecksNotExistsModel();
        if (checksNotExists.size() != 0) checksNotExists.forEach(System.out::println);
        else System.out.println("ALL CHECKS IN JAVA!");
        //checksNotExists.forEach(check::setNotInJava);
    }
}
