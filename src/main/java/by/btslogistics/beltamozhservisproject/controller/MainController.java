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
    @GetMapping("/")
    public String hello() throws IOException, ParserConfigurationException, SAXException {
        //xsdService.saveRootXsd(new File("EEC_R_030_DeclarantNotification_v1.2.0.xsd"));
        xsdService.saveFlkGrafa(new File("EEC_M_ComplexDataObjects_vbts4.xsd"));
//        xsdService.printFlkGrafaInfo(new File("EEC_M_ComplexDataObjects_vbts4.xsd"));
        return "hello";
    }

}
