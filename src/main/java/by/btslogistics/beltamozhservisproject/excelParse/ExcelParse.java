package by.btslogistics.beltamozhservisproject.excelParse;

import by.btslogistics.beltamozhservisproject.service.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class ExcelParse {

    public void excelParse() {
         try {
            FileInputStream inputStream = new FileInputStream(
                   new File("excelFiles/Справочник_ФЛК_УПДТ.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    ExcelService excelService = new ExcelService();
                    if (cell.getRowIndex() > 0) {
                        excelService.setToBase(cell, cell.getColumnIndex());
                    }
                    CellType cellType = cell.getCellType();
                    switch (cellType) {
                        case _NONE:
                            System.out.print("" + "\t");
                            break;
                        case BLANK:
                            System.out.print("" + "\t");
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        case  ERROR:
                            System.out.print("error" + "\t");
                            break;
                        case FORMULA:
                            System.out.print(cell.getCellFormula() + "\t");
                            break;
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t");
                            break;
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                    }
                }
                System.out.println("");
            }
        } catch (Exception e) {
             e.printStackTrace();
         }
    }
}
