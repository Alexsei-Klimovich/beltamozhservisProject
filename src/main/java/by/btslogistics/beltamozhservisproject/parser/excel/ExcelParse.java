package by.btslogistics.beltamozhservisproject.parser.excel;

import by.btslogistics.beltamozhservisproject.service.ExcelService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class ExcelParse {
    Map<Integer, String> excelMap;

    public void parseExcelFile() {
        try {
            FileInputStream inputStream = new FileInputStream(
                    new File("excelFiles/Справочник_ФЛК_УПДТ.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            System.out.println("Physical: " + sheet.getPhysicalNumberOfRows());
            System.out.println("Just last: " + sheet.getLastRowNum());
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();
                    Cell cell;
                    while (cellIterator.hasNext()) {
                        cell = cellIterator.next();
                        CellType cellType = cell.getCellType();
                        switch (cellType) {
                            case _NONE:
                                System.out.print("" + "\t");
                                break;
                            case NUMERIC:
                                System.out.print(cell.getNumericCellValue() + "\t");
                                break;
                            case STRING:
                                System.out.print(cell.getStringCellValue() + "\t");
                                break;
                            }
                        }
                    System.out.println("");
                    }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
