package by.btslogistics.beltamozhservisproject.controller;

import by.btslogistics.beltamozhservisproject.model.Check;
import by.btslogistics.beltamozhservisproject.model.Grafa;

import by.btslogistics.beltamozhservisproject.service.CheckService;
import by.btslogistics.beltamozhservisproject.service.ExcelService;
import by.btslogistics.beltamozhservisproject.service.GrafaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    GrafaService grafaService;
    @Autowired
    CheckService checkService;

    @Autowired
    ExcelService excelService;

    @GetMapping("/")
    public String test(){




        return "hello";
    }
}
