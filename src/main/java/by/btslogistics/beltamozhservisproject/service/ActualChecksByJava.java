package by.btslogistics.beltamozhservisproject.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActualChecksByJava {

    // метод достает проверки из java кода и ищет их по справочнику
    public void comparingChecksByJava(File excelFile, File javaFile) {
        List<String> parsedChecksFromDirectory = ExcelService.checksFromExcel(excelFile);
        List<String> checksNotExists = new ArrayList<>();
        List<String> parsedFile = new ArrayList<>();

        try {
            parsedFile = parseChecksFromJava(Files.readAllLines(Paths.get(String.valueOf(javaFile))));
            parsedFile.forEach(x -> {
                if (parsedChecksFromDirectory.stream().noneMatch(x::contains))
                    checksNotExists.add(x);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        saveToNotExist(checksNotExists);
        deleteCaseFromJava(checksNotExists, parsedFile);
    }

    private List<String> parseChecksFromJava(List<String> javaFile) {
        List<String> parsedChecks = new ArrayList<>();
        javaFile.forEach(x -> {
            if (x.contains("case") && x.contains("\"")) parsedChecks.add(x.split("\"")[1]);
        });
        return parsedChecks;
    }

    private void saveToNotExist(List<String> checksNotExists) {
        if (checksNotExists.size() != 0) checksNotExists.forEach(System.out::println);
    }

    private void deleteCaseFromJava(List<String> checksNotExists, List<String> parsedFile) {
        System.out.println("coming soon...");
    }

    //TODO write method which will backup source java file
}
