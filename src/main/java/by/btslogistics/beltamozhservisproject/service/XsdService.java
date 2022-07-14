package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.model.Grafa;
import by.btslogistics.beltamozhservisproject.model.StructureDocument;

import by.btslogistics.beltamozhservisproject.parser.xsd.XsdParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
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
                System.out.println();
                structureDocument.setRootElement(rootElement);
                structureDocument.setSchemaLocation(schemaLocation);
                structureDocument.setSchemaVersion(version);
                structureDocument.setSchemaName(schemaName);
                structureDocumentService.saveStructureDocument(structureDocument);
            }
        }
    }
    //TODO CREATE CHECK FOR FIELD EXISTS IN DB ADD PREFIX /SOMETHING  TO XML PATH
    public void saveFlkGrafa(File file) throws IOException, ParserConfigurationException, SAXException {
        Document document = XsdParser.buildDocumentFromFile(file);
        NodeList documentations = document.getElementsByTagName("xs:documentation");
        NodeList schemas = document.getElementsByTagName("xs:schema");
        Element schema =(Element) schemas.item(0);
        ///TODO CREATE METHOD TO SLIT ATTR PREFIX
        NamedNodeMap preTag = schema.getAttributes();
        String attr = String.valueOf(preTag.item(4));
        List<String> atrr = List.of(attr.split("="));
        List<String> splitedAttr = List.of(atrr.get(0).split(":"));
        String prefixAttribute = splitedAttr.get(1);
        /////
        String prefixPathXml  = "/";
        prefixPathXml+= prefixAttribute;
        prefixPathXml+=":";

        for (int i = 0; i < documentations.getLength(); i++) {
            Grafa grafa = new Grafa();
            String pathXml = "";

            Element element = (Element) documentations.item(i);
            Element parentElement = (Element) element.getParentNode().getParentNode();
            String attributeName = parentElement.getAttribute("name");
            String parentAttributeRef = parentElement.getAttribute("ref");

            if(attributeName.isEmpty()&& parentAttributeRef.isEmpty()){
                Element doubleParent = (Element) parentElement.getParentNode().getParentNode();
                String attributeRef = doubleParent.getAttribute("ref");
                pathXml = prefixPathXml+attributeRef;

            } else if(parentAttributeRef.isEmpty())
            {
                pathXml = prefixPathXml+attributeName;

            }else {
                pathXml = "/"+parentAttributeRef;
            }
            String formId = "UPDT";
            String nameGrafa=element.getTextContent();
            String namePole=element.getTextContent();

            System.out.println();
            grafa.setFormId(0L);//TODO String to Long parameter in entity
            grafa.setPathXML(pathXml);
            grafa.setNameGrafa(nameGrafa);
            grafa.setNamePole(namePole);
            grafaService.saveGrafa(grafa);
        }
    }

    public void printFlkGrafaInfo(File file) throws IOException, ParserConfigurationException, SAXException {
        Document document = XsdParser.buildDocumentFromFile(file);
        NodeList documentations = document.getElementsByTagName("xs:documentation");
        NodeList schemas = document.getElementsByTagName("xs:schema");
        Element schema =(Element) schemas.item(0);

        ///TODO CREATE METHOD TO SLIT ATTR PREFIX
        NamedNodeMap preTag = schema.getAttributes();
        String attr = String.valueOf(preTag.item(4));
        List<String> atrr = List.of(attr.split("="));
        List<String> splitedAttr = List.of(atrr.get(0).split(":"));
        String prefixAttribute = splitedAttr.get(1);
        /////


        String prefixPathXml  = "/";
        prefixPathXml+= prefixAttribute;
        prefixPathXml+=":";



        for (int i = 0; i < documentations.getLength(); i++) {
            String pathXml = "";

            Element element = (Element) documentations.item(i);
            Element parentElement = (Element) element.getParentNode().getParentNode();
            String attributeName = parentElement.getAttribute("name");
            String parentAttributeRef = parentElement.getAttribute("ref");

            if(attributeName.isEmpty()&& parentAttributeRef.isEmpty()){
                Element doubleParent = (Element) parentElement.getParentNode().getParentNode();
                String attributeRef = doubleParent.getAttribute("ref");
                pathXml = prefixPathXml+attributeRef;

            } else if(parentAttributeRef.isEmpty())
            {
                    pathXml = prefixPathXml+attributeName;

            }else {
                pathXml = "/"+parentAttributeRef;
            }
            String formId = "UPDT";
            String nameGrafa=element.getTextContent();
            String namePole=element.getTextContent();

            System.out.println();
            System.out.println("ID_FORM:"+formId);
            System.out.println("NAME_GRAFA:"+nameGrafa);
            System.out.println("NAME_POLE:"+namePole);
            System.out.println("PATH_XML:"+pathXml);
        }
    }
    public void printRootXsdInfo(File file) throws IOException, ParserConfigurationException, SAXException {
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
                System.out.println();
                System.out.println("SCHEMA_LOCATION:"+schemaLocation);
                System.out.println("ROOT_ELEMENT:"+rootElement);
                System.out.println("SCHEMA_VERSION:"+ version);
                System.out.println("SCHEMA_NAME:"+ schemaName);
            }
        }
    }



    public static void printXsdInfo(Document document) {
        NodeList documentations = document.getElementsByTagName("xs:documentation");
        System.out.println("__________________________________");
        for (int i = 0; i < documentations.getLength(); i++) {
            Element element = (Element) documentations.item(i);
            Element parentElement = (Element) element.getParentNode().getParentNode();
            if (parentElement.getAttribute("name").isEmpty()) {
                Element doubleParentNode = (Element) parentElement.getParentNode().getParentNode();
                System.out.println("name: " + doubleParentNode.getAttribute("name"));
                System.out.println("type: " + doubleParentNode.getAttribute("type"));
                System.out.println("parent: " + parentElement.getParentNode().getParentNode().getNodeName());
            } else {
                System.out.println("name: " + parentElement.getAttribute("name"));
                System.out.println("type: " + parentElement.getAttribute("type"));
                System.out.println("parent: " + element.getParentNode().getParentNode().getNodeName());
            }
            System.out.println("documentation: " + element.getTextContent());
            System.out.println("__________________________________");
        }
    }

}
