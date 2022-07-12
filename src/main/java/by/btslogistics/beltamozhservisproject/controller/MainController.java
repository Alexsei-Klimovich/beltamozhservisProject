package by.btslogistics.beltamozhservisproject.controller;

import by.btslogistics.beltamozhservisproject.model.Check;
import by.btslogistics.beltamozhservisproject.model.Grafa;
import by.btslogistics.beltamozhservisproject.repository.CheckRepository;
import by.btslogistics.beltamozhservisproject.service.CheckService;
import by.btslogistics.beltamozhservisproject.service.ExcelService;
import by.btslogistics.beltamozhservisproject.service.GrafaService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

@RestController
public class MainController {

    @Autowired
    GrafaService grafaService;
    @Autowired
    CheckService checkService;

    @Autowired
    CheckRepository checkRepository;

    @Autowired
    ExcelService excelService;
    public void saveToDb(Cell cell, int row) {
        Check check = new Check();
        Grafa grafa = new Grafa();

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
                if(checkValidation(check)){
                    checkRepository.save(check);
                }
//                grafaService.saveGrafa(grafa);
                break;
        }
    }
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
                ExcelService excelService = new ExcelService();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    if (cell.getRowIndex() > 0) {
                        saveToDb(cell, cell.getColumnIndex());
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
    @GetMapping("/")
    public String test(){
        Grafa grafa = new Grafa();
        Check check = new Check();
        excelParse();



        return "hello";
    }

    private boolean checkValidation(Check check){
        return check.getId()==null|| check.getCheckDescription()==null;

//        System.out.println("ID:"+check.getId()+"-"+check.getGrafaId()+"-");
//        return true;
    }
}
