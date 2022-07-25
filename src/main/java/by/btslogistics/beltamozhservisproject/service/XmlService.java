package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.exception.StructureDocumentAlreadyParsedException;
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
        String schemaLocation = rootXml.getPath().replace(".xml","");
        if(isStructureDocumentExists(schemaLocation)){
            throw new StructureDocumentAlreadyParsedException();
        }
        XmlParser xmlParser = new XmlParser(rootXml);
        Map<String, String> pathMap = xmlParser.getElementsPathAndNameMap(xmlParser.getChildrenPath());
        pathMap.put(xmlParser.getRootElementName(), xmlParser.getRootElementPath());
        Map<String, String> documentationMap = xmlParser.getPathAndDocumentationMap(xmlParser.getElementsPathAndNameMap(xmlParser.getChildrenPath()));
        documentationMap.put(xmlParser.getRootElementPath(),"Свидетельство о предоставленном обеспечении");//TODO: REMOVE
        xsdService.saveRootXsd(new File(rootXml.getName().replace(".xml", "")));
        StructureDocument structureDocument = structureDocumentService.getDocumentBySchemaLocation(schemaLocation);
        for (Map.Entry<String, String> entry : documentationMap.entrySet()) {
            Grafa grafa = new Grafa();
            grafa.setPathXML(entry.getKey());
            grafa.setNameGrafa(entry.getValue());
            grafa.setNamePole(entry.getValue());
            grafaService.saveGrafa(grafa);
            Tag tag = new Tag();
            tag.setPattern(xmlParser.getPatternForElementByName(pathMap.get(entry.getKey())));
            tag.setStructureDocument(structureDocument);
            tag.setNodePath(entry.getKey());
            tag.setParentPath(xmlParser.getParentElementPath(entry.getKey()));
            tag.setNodeName(entry.getValue());
            tagService.saveTag(tag);
        }
        for (Map.Entry<String, String> entry : pathMap.entrySet()) {
            Tag child = tagService.getTagByNodePath(entry.getKey());
            if (child!=null){
                if (child.getParentPath().length() > 2) {
                    Tag parent = tagService.getTagByNodePath(child.getParentPath());
                    if(parent!=null){
                        child.setParentName(parent.getNodeName());
                        child.setParentId(parent.getId());
                        tagService.updateTag(child);
                    }
                }
            }

        }
    }

    public boolean isStructureDocumentExists(String schemaLocation){
        StructureDocument document = structureDocumentService.getDocumentBySchemaLocation(schemaLocation);
        return document != null;
    }
}


