package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.model.Check;
import by.btslogistics.beltamozhservisproject.model.Grafa;
import by.btslogistics.beltamozhservisproject.model.Tag;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
            String codeCheck = "CODE_CHECK";
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

    //метод для выборки только данных из столбца code_check
    public static List<String> checksFromExcel(File file) {
        List<String> parsedRows = new ArrayList<>();
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
                    if (cellType == CellType.STRING && cell.getStringCellValue().equals("CODE_CHECK")) {
                        cellNum = cell.getColumnIndex();
                        flag = true;
                    } else if (cell.getColumnIndex() == cellNum && flag) {
                        result.append(cell.getStringCellValue().replace(" ", ""));
                    }
                }
                if (result.toString().length() > 0) {
                    parsedRows.add(result.toString());
                }
            }
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parsedRows;
    }
}
