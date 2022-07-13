package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.model.Check;
import by.btslogistics.beltamozhservisproject.model.Grafa;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ExcelService {

    @Autowired
    CheckService checkService;

    @Autowired
    GrafaService grafaService;

   public void saveParsedRows(List<String> parsedRows){

       for (int i = 1; i<parsedRows.size()-1;i++){
           List<String> splitString = List.of(parsedRows.get(i).split("/split/"));

           Check check = new Check();
           Grafa grafa = new Grafa();
           grafa.setPathXML(splitString.get(1));
           check.setGrafa(grafa);
           //Grafa grafa = new Grafa();
           //
           //grafaService.saveGrafa(grafa);
           check.setCheckCode(splitString.get(2));
           check.setCheckDescription(splitString.get(3));
           check.setErrorDescription(splitString.get(4));
           //check.setGrafaId(grafaService.getGrafaByPathXml(grafa.getPathXML()).getId());
           checkService.saveCheck(check);
       }

   }
}
