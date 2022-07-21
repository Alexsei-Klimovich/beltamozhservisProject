package by.btslogistics.beltamozhservisproject.controller;

import by.btslogistics.beltamozhservisproject.parser.xsd.XmlParser;
import by.btslogistics.beltamozhservisproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    GrafaService grafaService;

    @Autowired
    CheckService checkService;

    @Autowired
    ExcelService excelService;

    @Autowired
    StructureDocumentService structureDocumentService;
    @Autowired
    XsdService xsdService;

    @Autowired
    XmlService xmlService;


    @GetMapping("/")
    public String hello() throws IOException, ParserConfigurationException, SAXException {
//        xmlService.saveDocumentInfo(new File("BY_GuaranteeCertificate_1.0.1.xsd.xml"));
        excelService.saveParsedRows(ExcelService.excelParse(new File("ФЛК0722 для БД.xlsx")));
        return "hello";
    }

}
