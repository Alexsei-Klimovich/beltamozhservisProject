package by.btslogistics.beltamozhservisproject.parser.excel;

import by.btslogistics.beltamozhservisproject.model.Check;
import by.btslogistics.beltamozhservisproject.model.Grafa;
import by.btslogistics.beltamozhservisproject.service.CheckService;
import by.btslogistics.beltamozhservisproject.service.ExcelService;
import by.btslogistics.beltamozhservisproject.service.GrafaService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelParse {

    public static List<String> excelParse() {
        List<String> parsedRows = new ArrayList<>();

         try {
            FileInputStream inputStream = new FileInputStream(
                    "excelFiles/Справочник_ФЛК_УПДТ.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
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
                 if(result.toString().length()>0){
                     parsedRows.add(result.toString());
                 }
             }

        } catch (Exception e) {
             e.printStackTrace();
         }
         return parsedRows;
    }

}
