package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.model.Check;
import by.btslogistics.beltamozhservisproject.model.Grafa;
import org.apache.poi.ss.usermodel.Cell;

import java.util.ArrayList;
import java.util.List;

public class ExcelService {
    public void setToBase(Cell cell, int row) {
        Check check = new Check();
        Grafa grafa = new Grafa();
        CheckService checkService = new CheckService();
        GrafaService grafaService = new GrafaService();
        switch (row) {
            case 0:
                check.setId((long) cell.getNumericCellValue());
                break;
            case 1:
                grafa.setPathXML(cell.getStringCellValue());
                break;
            case 2:
                check.setCheckCode(cell.getStringCellValue());
                break;
            case 3:
                check.setCheckDescription(cell.getStringCellValue());
                break;
            case 4:
                check.setErrorDescription(cell.getStringCellValue());
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                check.setGrafaId((long) cell.getNumericCellValue());
                grafa.setId((long) cell.getNumericCellValue());
                break;
            case 8:
                checkService.saveCheck(check);
                grafaService.saveGrafa(grafa);
                break;
        }
    }
}
