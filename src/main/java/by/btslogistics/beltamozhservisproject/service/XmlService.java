package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.model.Grafa;
import by.btslogistics.beltamozhservisproject.model.StructureDocument;
import by.btslogistics.beltamozhservisproject.model.Tag;
import by.btslogistics.beltamozhservisproject.parser.xsd.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Map;

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

    public void saveDocumentInfo(File rootXml) throws IOException, ParserConfigurationException, SAXException {
        XmlParser xmlParser = new XmlParser(rootXml);
        Map<String, String> pathMap = xmlParser.getElementsPathMap(xmlParser.getElementsName(xmlParser.getChildrenPath()), xmlParser.getChildrenPath());
        pathMap.put(xmlParser.getRootElementName(), xmlParser.getRootElementPath());
        Map<String, String> documentationMap = xmlParser.getElementsDocumentationMap(xmlParser.getElementsName(xmlParser.getChildrenPath()));
        xsdService.saveRootXsd(new File(rootXml.getName().replace(".xml", "")));
        for (Map.Entry<String, String> entry : documentationMap.entrySet()) {
            Grafa grafa = new Grafa();
            grafa.setPathXML(pathMap.get(entry.getKey()));
            grafa.setNameGrafa(entry.getValue());
            grafa.setNamePole(entry.getValue());
            grafaService.saveGrafa(grafa);

            //TODO CREATE FIND  DOCUMENT METHOD HERE
            StructureDocument structureDocument = structureDocumentService.getDocumentBySchemaName("DeclarantNotification");

            Tag tag = new Tag();
            tag.setPattern(xmlParser.getPatternForElement(entry.getKey()));
            tag.setStructureDocument(structureDocument);
            tag.setParentPath(xmlParser.getParentElementPath(pathMap.get(entry.getKey())));
            tag.setNodeName(entry.getValue());
            tag.setNodePath(pathMap.get(entry.getKey()));
            tagService.saveTag(tag);
        }
        for (Map.Entry<String, String> entry : documentationMap.entrySet()) {
            Tag child = tagService.getTagByNodePath(pathMap.get(entry.getKey()));
            if (child.getParentPath().length() > 2) {
                Tag parent = tagService.getTagByNodePath(child.getParentPath());
                child.setParentPath(parent.getNodePath());
                child.setParentName(parent.getNodeName());
                child.setParentId(parent.getId());
                tagService.updateTag(child);
            }
        }
    }
}


