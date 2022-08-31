package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.exception.StructureDocumentAlreadyParsedException;
import by.btslogistics.beltamozhservisproject.model.*;
import by.btslogistics.beltamozhservisproject.parser.xsd.XmlParser;
import by.btslogistics.beltamozhservisproject.parser.xsd.XsdParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class XmlService {
    @Autowired
    GrafaService grafaService;
    @Autowired
    TagService tagService;
    @Autowired
    StructureDocumentService structureDocumentService;
    @Autowired
    XsdService xsdService;

    @Autowired
    KindDocumentService kindDocumentService;

    @Autowired
    KindStructureService kindStructureService;

    @Autowired
    TypeControlService typeControlService;

    //TODO:REFACTOR
    public void saveDocumentInfo(File rootXml) throws IOException, ParserConfigurationException, SAXException {
        String schemaLocation = rootXml.getPath().replace(".xml", "");
        if (isStructureDocumentExists(schemaLocation)) {
            throw new StructureDocumentAlreadyParsedException();
        }
        XmlParser xmlParser = new XmlParser(rootXml);
        Map<String, String> pathMap = xmlParser.getElementsPathAndNameMap(xmlParser.getChildrenPath());
        pathMap.put(xmlParser.getRootElementName(), xmlParser.getRootElementPath());
        Map<String, String> documentationMap = xmlParser.getPathAndDocumentationMap(xmlParser.getElementsPathAndNameMap(xmlParser.getChildrenPath()));
        documentationMap.put(xmlParser.getRootElementPath(), xmlParser.getRootElementDocumentation());
        xsdService.saveRootXsd(new File(rootXml.getName().replace(".xml", "")));
        StructureDocument structureDocument = structureDocumentService.getDocumentBySchemaLocation(schemaLocation);
        ///////////////////////////////////TODO: REFACTOR
        KindDocument kindDocument = new KindDocument();
        kindDocument.setDescription("Уведомление об отсутствии необходимости внесения изменений (дополнений) в сведения, заявленные в таможенной декларации, поданной при предварительном таможенном декларировании товаров");
        kindDocument.setCodeEng("UDPT");
        kindDocument.setCodeRus("УПДТ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime activateTime = LocalDateTime.parse("1995-01-01 00:00:00", formatter);
        LocalDateTime deactivateTime = LocalDateTime.parse("4712-12-31 00:00:00", formatter);
        kindDocument.setActivateDateDocument(activateTime);
        kindDocument.setDeactivateDateDocument(deactivateTime);
        ////////////////////////////////////TODO:REFACTOR
        KindStructure kindStructure = new KindStructure();
        kindStructure.setActivateDateDStructure(activateTime);
        kindStructure.setDeactivateDateStructure(deactivateTime);
        kindStructure.setStructureDocument(structureDocument);
        kindDocument.setKindStructure(kindStructure);
        kindStructure.setKindDocument(kindDocument);
        ////////////////////////////////////TODO:REFACTOR
        TypeControl typeControl = new TypeControl();
        typeControl.setDescription("Уведомление об отсутствии необходимости внесения изменений (дополнений) в сведения, заявленные в таможенной декларации, поданной при предварительном таможенном декларировании товаров");
        typeControl.setNameType("UPDT");
        typeControl.setStartCheckTime(LocalDateTime.parse("2020-01-01 00:00:00", formatter));
        typeControl.setEndCheckTime(deactivateTime);
        typeControl.setIsActive("1");
        typeControl.setDefaultControl(1L);
        typeControl.setCreateDate(LocalDateTime.parse("2020-01-01 00:00:00", formatter));
        typeControlService.saveTypeControl(typeControl);
        kindStructure.setTypeControl(typeControl);
        kindStructureService.saveKindStructure(kindStructure);
        kindDocumentService.saveKindDocument(kindDocument);

        for (Map.Entry<String, String> entry : documentationMap.entrySet()) {
            Grafa grafa = new Grafa();
            grafa.setPathXML(entry.getKey());
            grafa.setNameGrafa(entry.getValue());
            grafa.setNamePole(entry.getValue());
            grafaService.saveGrafa(grafa);
            Tag tag = new Tag();
            tag.setPattern(xmlParser.getPatternForElementByName(pathMap.get(entry.getKey())));
            tag.setMultiplicity(xmlParser.getConditionByMultiplicity(pathMap.get(entry.getKey())));
            tag.setStructureDocument(structureDocument);
            tag.setNodePath(entry.getKey());
            tag.setParentPath(xmlParser.getParentElementPath(entry.getKey()));
            tag.setNodeName(entry.getValue());
            tagService.saveTag(tag);
        }
        for (Map.Entry<String, String> entry : pathMap.entrySet()) {
            Tag child = tagService.getTagByNodePath(entry.getKey());
            if (child != null) {
                if (child.getParentPath().length() > 2) {
                    Tag parent = tagService.getTagByNodePath(child.getParentPath());
                    if (parent != null) {
                        child.setParentName(parent.getNodeName());
                        child.setParentId(parent.getId());
                        tagService.updateTag(child);
                    }
                }
            }

        }
    }

    public boolean isStructureDocumentExists(String schemaLocation) {
        StructureDocument document = structureDocumentService.getDocumentBySchemaLocation(schemaLocation);
        return document != null;
    }


    public void saveFlkGrafaAndTagDocument(File rootXml) throws IOException, ParserConfigurationException, SAXException {
        XmlParser xmlParser = new XmlParser(rootXml);
        List<String> resultPaths = new ArrayList<>();
        String rootElementWithPrefix = getRootElementWithPrefix(rootXml);
        List<String> childPaths = xmlParser.getChildrenPath();
        System.out.println("SIZE:" + childPaths.size());
        Map<String, String> newPrefixMapWithOutRootPrefix = getNewPrefixMapWithOutRootPrefix(rootXml);
        List<String> keys = new ArrayList<>();
        for (Map.Entry e : newPrefixMapWithOutRootPrefix.entrySet()) {
            keys.add(e.getKey().toString());
        }
        for (String path : childPaths) {
            for (String key : keys) {
                if (path.contains(key)) {
                    path = path.replaceAll(key, newPrefixMapWithOutRootPrefix.get(key));
                }
            }
            path = path.replaceAll("//", "/");
            path = path.replaceAll("::", ":");
            resultPaths.add(rootElementWithPrefix+path);
        }
        StructureDocument structureDocument = new StructureDocument();
        resultPaths.add(rootElementWithPrefix);
        resultPaths.forEach(System.out::println);
        Map<String, String> documentationMap = xmlParser.getPathAndDocumentationMap(xmlParser.getElementsPathAndNameMap(resultPaths));
        Map<String, String> pathMap = xmlParser.getElementsPathAndNameMap(resultPaths);
        for (Map.Entry<String, String> entry : documentationMap.entrySet()) {
            Grafa grafa = new Grafa();
            grafa.setPathXML(entry.getKey());
            grafa.setNameGrafa(entry.getValue());
            grafa.setNamePole(entry.getValue());
            grafaService.saveGrafa(grafa);
            Tag tag = new Tag();
            tag.setPattern(xmlParser.getPatternForElementByName(pathMap.get(entry.getKey())));
            tag.setMultiplicity(xmlParser.getConditionByMultiplicity(pathMap.get(entry.getKey())));
            tag.setStructureDocument(structureDocument);
            tag.setNodePath(entry.getKey());
            tag.setParentPath(xmlParser.getParentElementPath(entry.getKey()));
            tag.setNodeName(entry.getValue());
            tagService.saveTag(tag);
        }
        for (Map.Entry<String, String> entry : pathMap.entrySet()) {
            Tag child = tagService.getTagByNodePath(entry.getKey());
            if (child != null) {
                if (child.getParentPath().length() > 2) {
                    Tag parent = tagService.getTagByNodePath(child.getParentPath());
                    if (parent != null) {
                        child.setParentName(parent.getNodeName());
                        child.setParentId(parent.getId());
                        tagService.updateTag(child);
                    }
                }
            }
        }

        List<Tag> allTags = tagService.getAllTags();
        for (Tag tag: allTags){
            if(tag.getNodePath().contains("@")){
                String nodeName = tag.getNodeName();
                tag.setNodeName(tag.getParentName()+": "+nodeName);
                tagService.saveTag(tag);
            }
        }





    }



    public Map<String, String> getNewPrefixMapWithOutRootPrefix(File rootXml) throws IOException, ParserConfigurationException, SAXException {
        Document document = XsdParser.buildDocumentFromFile(rootXml);
        Node node = document.getDocumentElement();
        NamedNodeMap tags = node.getAttributes();
        Map<String, String> prefixMap = new HashMap<>();
        for (int i = 0; i < tags.getLength(); i++) {
            if (tags.item(i).toString().contains("xmlns")) {
                List<String> splitedTags = List.of(tags.item(i).toString().split("="));
                String fileName = List.of(tags.item(i).toString().split("\"")).get(1).toString().
                        replace(":", "_").replace("urn_", "") + ".xsd";
                String oldPrefix = splitedTags.get(0).replace("xmlns:", "");
                prefixMap.put(oldPrefix, fileName);
            }
        }
        Map<String, String> newPrefixMap = new HashMap<>();
        List<String> keys = new ArrayList<>();
        for (Map.Entry<String, String> entry : prefixMap.entrySet()) {
            keys.add(entry.getKey());
            newPrefixMap.put(entry.getKey(), XsdService.getPathPrefixFromFile(new File(entry.getValue())));
        }
        newPrefixMap.remove("urn");
        return newPrefixMap;
    }

    public String getRootElementWithPrefix(File rootXml) throws IOException, ParserConfigurationException, SAXException {
        Document document = XsdParser.buildDocumentFromFile(rootXml);
        Node node = document.getDocumentElement();
        NamedNodeMap tags = node.getAttributes();
        Map<String, String> prefixMap = new HashMap<>();
        for (int i = 0; i < tags.getLength(); i++) {
            if (tags.item(i).toString().contains("xmlns")) {
                List<String> splitedTags = List.of(tags.item(i).toString().split("="));
                String fileName = List.of(tags.item(i).toString().split("\"")).get(1).toString().
                        replace(":", "_").replace("urn_", "") + ".xsd";
                String oldPrefix = splitedTags.get(0).replace("xmlns:", "");
                prefixMap.put(oldPrefix, fileName);
            }
        }
        Map<String, String> newPrefixMap = new HashMap<>();
        List<String> keys = new ArrayList<>();
        for (Map.Entry<String, String> entry : prefixMap.entrySet()) {
            keys.add(entry.getKey());
            newPrefixMap.put(entry.getKey(), XsdService.getPathPrefixFromFile(new File(entry.getValue())));
        }
        XmlParser xmlParser = new XmlParser(rootXml);
        return newPrefixMap.get("urn")+xmlParser.getRootElementName();
    }


}


