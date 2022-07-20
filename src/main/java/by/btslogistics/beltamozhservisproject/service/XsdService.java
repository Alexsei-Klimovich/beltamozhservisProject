package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.model.Grafa;
import by.btslogistics.beltamozhservisproject.model.StructureDocument;

import by.btslogistics.beltamozhservisproject.parser.xsd.XsdParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static by.btslogistics.beltamozhservisproject.parser.xsd.XsdParser.buildDocumentFromFile;

@Service
public class XsdService {
    @Autowired
    StructureDocumentService structureDocumentService;

    @Autowired
    GrafaService grafaService;

    public void saveRootXsd(File file) throws IOException, ParserConfigurationException, SAXException {
        StructureDocument structureDocument = new StructureDocument();
        Document document = buildDocumentFromFile(file);
        NodeList elements = document.getElementsByTagName("xs:element");
        NodeList schemas = document.getElementsByTagName("xs:schema");
        Element schema =(Element) schemas.item(0);
        String version = schema.getAttribute("version");
        for (int i = 0; i < elements.getLength(); i++) {
            Element element = (Element) elements.item(i);
            if(element.hasAttribute("type")){
                String rootElement = element.getAttribute("type").replace("Type","");
                String schemaName = element.getAttribute("name");
                String schemaLocation = file.getPath();
                structureDocument.setRootElement(rootElement);
                structureDocument.setSchemaLocation(schemaLocation);
                structureDocument.setSchemaVersion(version);
                structureDocument.setSchemaName(schemaName);
                structureDocumentService.saveStructureDocument(structureDocument);
            }
        }
    }

    public static String getPathPrefixFromFile(File file) throws IOException, ParserConfigurationException, SAXException {
        Document document = XsdParser.buildDocumentFromFile(file);
        NodeList schemas = document.getElementsByTagName("xs:schema");
        Element schema =(Element) schemas.item(0);
        NamedNodeMap tags = schema.getAttributes();
        for (int i = 0; i<tags.getLength();i++){
            if(tags.item(i).toString().contains("xmlns")){
                List<String> compareStrings = List.of(tags.item(i).toString().split(":"));
                String compareString = (compareStrings.get(compareStrings.size()-1).replace("\"",""));
                if (file.getName().contains(compareString)){
                    List<String> firstSplit = List.of(tags.item(i).toString().split("="));
                    List<String> result = List.of(firstSplit.get(0).split(":"));
                    return "/"+result.get(1)+":";
                }
            }
        }
        return null;
    }
}
