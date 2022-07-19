package by.btslogistics.beltamozhservisproject.controller;

import by.btslogistics.beltamozhservisproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

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
        xmlService.saveDocumentInfo(new File("EEC_R_030_DeclarantNotification_v1.2.0.xsd.xml"));
        return "hello";
    }

}
