package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.model.Check;
import by.btslogistics.beltamozhservisproject.model.Grafa;
import by.btslogistics.beltamozhservisproject.model.Tag;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class ExcelService {
    @Autowired
    CheckService checkService;

    @Autowired
    TagService tagService;

    @Autowired
    GrafaService grafaService;

    public void saveParsedRows(List<String> parsedRows) {
        for (int i = 1; i < parsedRows.size(); i++) {
            List<String> splitString = List.of(parsedRows.get(i).split("/split/"));
            Check check = new Check();
            Tag tag = tagService.getTagByNodePath(splitString.get(0));
            System.out.println("PATH:" + splitString.get(0));
            Grafa grafa = grafaService.getGrafaByPathXml(splitString.get(0));
            if (grafa != null && tag != null) {
                check.setGrafaId(grafa.getId());
                check.setTag(tag);
                check.setGrafa(grafa);
                check.setCheckCode(splitString.get(1));
                check.setCheckDescription(splitString.get(2));
                check.setErrorDescription(splitString.get(3));
                check.setGrafa(grafa);
                checkService.saveCheck(check);
            } else {
                System.out.println("ERRORHERE:" + splitString.get(0));
            }
        }
    }

    public static List<String> excelParse(File file) {
        List<String> parsedRows = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                StringBuilder result = new StringBuilder();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    CellType cellType = cell.getCellType();
                    switch (cellType) {
                        case NUMERIC:
                            result.append(cell.getNumericCellValue()).append("/split/");
                            break;
                        case STRING:
                            result.append(cell.getStringCellValue()).append("/split/");
                            break;
                    }
                }
                if (result.toString().length() > 0) {
                    parsedRows.add(result.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parsedRows;
    }

    public static LinkedHashMap<String, Integer> checksFromExcel(File file) {
        LinkedHashMap<String, Integer> parsedRows = new LinkedHashMap<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int cellNum = 0;
            boolean flag = false;
            for (Row row : sheet) {
                StringBuilder result = new StringBuilder();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    CellType cellType = cell.getCellType();
                    if (cellType == CellType.STRING && cell.getStringCellValue().equals("ROOT_PATH")) {
                        cellNum = cell.getColumnIndex();
                        flag = true;
                    } else if (cell.getColumnIndex() == cellNum && flag) {
                        result.append(cell.getStringCellValue().replace(" ", ""));
                    }
                }
                if (result.toString().length() > 0) parsedRows.put(result.toString(), Arrays.asList(result.toString().split("/")).size() - 1);
            }
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parsedRows;
    }

    public static LinkedHashMap<String, Integer> pathFromExcel(File file) {
        LinkedHashMap<String, Integer> parsedRows = new LinkedHashMap<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int cellNum = 0;
            boolean flag = false;
            for (Row row : sheet) {
                StringBuilder result = new StringBuilder();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    CellType cellType = cell.getCellType();
                    if (cellType == CellType.STRING && cell.getStringCellValue().equals("NODE_PATH")) {
                        cellNum = cell.getColumnIndex();
                        flag = true;
                    } else if (cell.getColumnIndex() == cellNum && flag) {
                        result.append(cell.getStringCellValue().replace(" ", ""));
                    }
                }
                if (result.toString().length() > 0) parsedRows.put(result.toString(), Arrays.asList(result.toString().split("/")).size());
            }
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parsedRows;
    }

    public void setRootByPath(File tags, File root) {
        LinkedHashMap<String, Integer> pathes = pathFromExcel(tags);
        LinkedHashMap<String, Integer> roots = checksFromExcel(root);
        List<String> areaToConsole = new ArrayList<>();

        for (Map.Entry<String, Integer> path : pathes.entrySet()) {
            String rootPath = findRootPath(roots, path.getKey());
            areaToConsole.add("UPDATE TTS_CONFIGURATION.TAG_DOCUMENT SET ROOT_PATH = '" + rootPath +"' WHERE TO_STRDOC_ID = 37 AND NODE_PATH = '" + path.getKey() + "';");
        }
        areaToConsole.forEach(System.out::println);
    }

    private String findRootPath(LinkedHashMap<String, Integer> areas, String path) {
        String area = null;
        int size = Arrays.asList(path.split("/")).size() - 2;
        if (size == 1 || size == 2) {
            return checkForRootPath(areas, path, 1);
        } else if (size == 3) {
            return checkForRootPath(areas, path, 2);
        } else {
            return checkForRootPath(areas, path, size);
        }
    }

    private String checkForRootPath(LinkedHashMap<String, Integer> areas, String path, int i) {
        if (i == 1 || i == 2){
            for (Map.Entry<String, Integer> ar : areas.entrySet()) {
                if (Objects.equals(ar.getValue(), i) && path.contains(ar.getKey()) && !ar.getKey().trim().isEmpty())
                    return ar.getKey();
            }
        } else {
            for (Map.Entry<String, Integer> ar : areas.entrySet()) {
                if (i >= 4 && ar.getValue() >= 3 && path.contains(ar.getKey()) && !ar.getKey().trim().isEmpty())
                    return ar.getKey();
            }
        }
        return "customsDocumentCURiskObject";
    }



}
