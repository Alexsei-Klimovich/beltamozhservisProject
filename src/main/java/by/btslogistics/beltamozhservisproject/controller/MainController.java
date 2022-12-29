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

    @Autowired
    ActualByDirectoryService actualByDirectoryService;

    @Autowired
    ActualChecksByJava actualChecksByJava;

    @GetMapping("/")
    public String hello() throws IOException, ParserConfigurationException, SAXException {
        xmlService.saveFlkGrafaAndTagDocument(new File("04CustomsDocumentCURiskObject.xsd.xml"));
        return "hello";
    }

    @GetMapping("/compare/directory")
    public String compareDirectory() {
        actualByDirectoryService.comparingChecksByDirectory(new File("excel.xlsx"), //excel directory
                                                            new File("java.txt")); //java code
        return "compare is directory-main done!";
    }

    @GetMapping("/compare/java")
    public String compareJava() throws IOException {
        actualChecksByJava.comparingChecksByJava(new File("excel.xlsx"), //excel directory
                                                 new File("java.txt"),
                                                 new File("/Users/yarsh/Desktop/beltamozhservisProject/output.txt")); //java code
        return "compare java-main is done!";
    }

}
