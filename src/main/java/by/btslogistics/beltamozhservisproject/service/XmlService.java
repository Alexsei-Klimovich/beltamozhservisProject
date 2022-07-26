package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.exception.StructureDocumentAlreadyParsedException;
import by.btslogistics.beltamozhservisproject.model.*;
import by.btslogistics.beltamozhservisproject.parser.xsd.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

    @Autowired
    KindDocumentService kindDocumentService;

    @Autowired
    KindStructureService kindStructureService;

    @Autowired
    TypeControlService typeControlService;
    //TODO:REFACTOR
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
        ///////////////////////////////////TODO: REFACTOR
        KindDocument kindDocument = new KindDocument();
        kindDocument.setDescription("Уведомление об отсутствии необходимости внесения изменений (дополнений) в сведения, заявленные в таможенной декларации, поданной при предварительном таможенном декларировании товаров");
        kindDocument.setCodeEng("UDPT");
        kindDocument.setCodeRus("УПДТ");
        DateTimeFormatter formatter  =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime activateTime = LocalDateTime.parse("1995-01-01 00:00:00",formatter);
        LocalDateTime deactivateTime = LocalDateTime.parse("4712-12-31 00:00:00",formatter);
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
        typeControl.setStartCheckTime(LocalDateTime.parse("2020-01-01 00:00:00",formatter));
        typeControl.setEndCheckTime(deactivateTime);
        typeControl.setIsActive("1");
        typeControl.setDefaultControl(1L);
        typeControl.setCreateDate(LocalDateTime.parse("2020-01-01 00:00:00",formatter));
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

//    public void saveKindDocument(){
//        KindDocument kindDocument = new KindDocument();
//        kindDocument.setDescription("Уведомление об отсутствии необходимости внесения изменений (дополнений) в сведения, заявленные в таможенной декларации, поданной при предварительном таможенном декларировании товаров");
//        kindDocument.setCodeEng("UDPT");
//        kindDocument.setCodeRus("УПДТ");
//
//        kindDocument.setKindStructure(new KindStructure());
//        DateTimeFormatter formatter  =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime activateTime = LocalDateTime.parse("1995-01-01 00:00:00",formatter);
//        LocalDateTime deactivateTime = LocalDateTime.parse("4712-12-31 00:00:00",formatter);
//        kindDocument.setActivateDateDocument(activateTime);
//        kindDocument.setDeactivateDateDocument(deactivateTime);
//        kindDocumentService.saveKindDocument(kindDocument);
//    }
//    public void saveKindStructure(Long toKindDocumentId, Long toStrDocId){
//        KindStructure kindStructure = new KindStructure();
//        kindStructure.setToKindId(toKindDocumentId);
//        kindStructure.setToStructDocId(toStrDocId);
//        DateTimeFormatter formatter  =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime activateTime = LocalDateTime.parse("1995-01-01 00:00:00",formatter);
//        LocalDateTime deactivateTime = LocalDateTime.parse("4712-12-31 00:00:00",formatter);
//        kindStructure.setActivateDateDStructure(activateTime);
//        kindStructure.setDeactivateDateStructure(deactivateTime);
//        kindStructureService.saveKindStructure(kindStructure);
//    }


}


