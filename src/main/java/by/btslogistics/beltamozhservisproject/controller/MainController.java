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

    @Autowired
    ActualByDirectoryService actualByDirectoryService;

    @Autowired
    ActualChecksByJava actualChecksByJava;

    @Autowired
    ChangeCodeCheckService changeCodeCheckService;

    @GetMapping("/")
    public String hello() throws IOException, ParserConfigurationException, SAXException {
        xmlService.saveFlkGrafaAndTagDocument(new File("04CustomsDocumentCURiskObject.xsd.xml"));
        return "hello";
    }

    /*
    find checks which not exist in java code
        @param file - excel directory file
        @param file - java code file
    */
    @GetMapping("/compare/directory")
    public String compareDirectory() {
        actualByDirectoryService.comparingChecksByDirectory(new File("excel.xlsx"),
                                                            new File("java.txt"));
        return "compare is directory-main done!";
    }

    /*
    find checks which not exist in directory and then deleting this checks from java code
        @param file - excel directory file
        @param file - java code file
        @param file - result java code file
    */
    @GetMapping("/compare/java")
    public String compareJava() throws IOException {
        actualChecksByJava.comparingChecksByJava(new File("excel.xlsx"),
                                                 new File("java.txt"),
                                                 new File("/Users/yarsh/Desktop/beltamozhservisProject/output.txt"));
        return "compare java-main is done!";
    }

    @GetMapping("/replace")
    public String replaceCodeChecks() {
        changeCodeCheckService.findCodeChecks(new File("testForReplace.xlsx"));
        return "replace is done!";
    }

}
