package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.model.Check;
import by.btslogistics.beltamozhservisproject.model.Grafa;
import by.btslogistics.beltamozhservisproject.model.StructureDocument;
import by.btslogistics.beltamozhservisproject.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class InsertsSqlService {
    @Autowired
    StructureDocumentService structureDocumentService;

    @Autowired
    GrafaService grafaService;

    @Autowired
    TagService tagService;

    @Autowired
    CheckService checkService;

    public List<String> getGrafaInserts(){
        List<Grafa> grafs = grafaService.getAllGrafs();
        List<String> grafaInserts = new ArrayList<>();
        for (Grafa grafa: grafs){
            String insertRow = String.format("INSERT INTO public.flk_grafa (id, id_form, name_grafa, name_pole, path_xml) VALUES (%s, %s, '%s', '%s', '%s');\n",
                    grafa.getId(),grafa.getFormId(),grafa.getNameGrafa(),grafa.getNamePole(),grafa.getPathXML());
            grafaInserts.add(insertRow);
        }
        return grafaInserts;
    }

    public List<String> getCheckInserts(){
        List<Check> checks = checkService.getAllChecks();
        List<String> checksInserts = new ArrayList<>();
        for (Check check: checks){
            String insertRow = String.format("INSERT INTO public.flk_checks (id, id_grafa, to_tag_doc_id, code_check, description_check, description_error, d_on, d_off) VALUES (%s, %s, %s, '%s', '%s', '%s', %s, %s);\n",
                    check.getId(),check.getGrafaId(),check.getToTagDocId(),
                    check.getCheckCode(),check.getCheckDescription(),check.getErrorDescription(),check.getStartCheckTime(),check.getEndCheckTime());
            checksInserts.add(insertRow);
        }
        return checksInserts;
    }

    public List<String> getStructureDocumentInserts(){
        List<StructureDocument> structureDocuments = structureDocumentService.getAllStructureDocuments();
        List<String> structureDocumentInserts = new ArrayList<>();
        for (StructureDocument structureDocument: structureDocuments){
            String insertRow = String.format("INSERT INTO public.structure_document (id, schema_location, root_element, schema_version, schema_name) VALUES (%s, '%s', '%s', '%s', '%s');\n",
                    structureDocument.getId(),structureDocument.getSchemaLocation(),structureDocument.getRootElement(), structureDocument.getSchemaVersion(),structureDocument.getSchemaName());
            structureDocumentInserts.add(insertRow);
        }
        return structureDocumentInserts;
    }

    public List<String> getTagInserts(){
        List<Tag> tags = tagService.getAllTags();
        List<String> tagInserts = new ArrayList<>();
        for (Tag tag: tags){
            String insertRow = String.format("INSERT INTO public.tag_document (id, to_strdoc_id, node_name, node_path, parent_name, parent_path, pattern, parent_id) VALUES (%s, %s, '%s', '%s', %s, '%s', %s, %s);\n",
                    tag.getId(),tag.getToStrdocId(),tag.getNodeName(),tag.getNodePath(),tag.getParentName(),tag.getParentPath(),tag.getPattern(),tag.getParentId());
            tagInserts.add(insertRow);
        }
        return tagInserts;
    }

    public void createSqlInsertFiles() throws IOException {
        Files.write(Paths.get("flk_grafa.sql"), getGrafaInserts(), StandardOpenOption.CREATE);
        Files.write(Paths.get("flk_checks.sql"), getCheckInserts(), StandardOpenOption.CREATE);
        Files.write(Paths.get("tag_document.sql"), getTagInserts(), StandardOpenOption.CREATE);
        Files.write(Paths.get("structure_document.sql"), getStructureDocumentInserts(), StandardOpenOption.CREATE);
    }



}

