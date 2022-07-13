package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.model.Check;
import by.btslogistics.beltamozhservisproject.model.Grafa;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
public class ExcelService {
    @Autowired
    CheckService checkService;

    @Autowired
    GrafaService grafaService;

   public void saveParsedRows(List<String> parsedRows){
       for (int i = 1; i<parsedRows.size(); i++){
           List<String> splitString = List.of(parsedRows.get(i).split("/split/"));
           Check check = new Check();
           Grafa grafa = new Grafa();
           grafa.setPathXML(splitString.get(1));
           check.setGrafa(grafa);
           check.setCheckCode(splitString.get(2));
           check.setCheckDescription(splitString.get(3));
           check.setErrorDescription(splitString.get(4));
           checkService.saveCheck(check);
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
