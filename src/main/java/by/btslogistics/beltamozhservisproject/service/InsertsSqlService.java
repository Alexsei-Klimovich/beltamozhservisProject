package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.model.*;
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
    @Autowired
    KindDocumentService kindDocumentService;
    @Autowired
    KindStructureService kindStructureService;
    @Autowired
    TypeControlService typeControlService;

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

    public List<String> getKindDocumentInserts(){
        List<KindDocument> kindDocuments = kindDocumentService.getAllKindDocuments();
        List<String> kindDocumentInserts = new ArrayList<>();
        for (KindDocument kindDocument: kindDocuments){
            String insertRow = String.format("INSERT INTO public.kind_document (id, code_eng, code_rus, description, date_activate, date_deactivate) VALUES (%s, '%s', '%s', '%s', '%s', '%s');\n",
                    kindDocument.getId(),kindDocument.getCodeEng(),kindDocument.getCodeRus(),kindDocument.getActivateDateDocument(),kindDocument.getDeactivateDateDocument());
            kindDocumentInserts.add(insertRow);
        }
        return kindDocumentInserts;
    }

    public List<String> getTypeControlInserts(){
        List<TypeControl> typeControls = typeControlService.getAllTypeControls();
        List<String> typeControlInserts = new ArrayList<>();
        for (TypeControl typeControl: typeControls){
            String insertRow = String.format("INSERT INTO public.flk_type_control (id, name_type, description, d_on, d_off, is_active, default_control, date_create, date_update) VALUES (%s, '%s', '%s', '%s', '%s', '%s', %s, '%s', '%s');\n",
                    typeControl.getId(),typeControl.getNameType(),typeControl.getDescription(),typeControl.getStartCheckTime(),
                    typeControl.getEndCheckTime(),typeControl.getIsActive(),typeControl.getDefaultControl(),typeControl.getCreateDate(),typeControl.getUpdateDate());
            typeControlInserts.add(insertRow);
        }
        return typeControlInserts;
    }

    public List<String> getKindStructureInserts(){
        List<KindStructure> kindStructures = kindStructureService.getAllKindStructures();
        List<String> kindStructureInserts = new ArrayList<>();
        for(KindStructure kindStructure: kindStructures){
            String insertRow = String.format("INSERT INTO public.kind_m2m_structure (id, to_kind_id, to_struct_doc_id, date_activate, date_deactivate, to_flk_type_cntrl_id) VALUES (%s, %s, %s, '%s', '%s', %s);\n",
                    kindStructure.getId(),kindStructure.getToKindId(),kindStructure.getToStructDocId(),
                    kindStructure.getActivateDateDStructure(),kindStructure.getDeactivateDateStructure(),kindStructure.getToFlkTypeCntrlId());
            kindStructureInserts.add(insertRow);
        }
        return kindStructureInserts;
    }


    public void createSqlInsertFiles() throws IOException {
        Files.write(Paths.get("flk_grafa.sql"), getGrafaInserts(), StandardOpenOption.CREATE);
        Files.write(Paths.get("flk_checks.sql"), getCheckInserts(), StandardOpenOption.CREATE);
        Files.write(Paths.get("tag_document.sql"), getTagInserts(), StandardOpenOption.CREATE);
        Files.write(Paths.get("structure_document.sql"), getStructureDocumentInserts(), StandardOpenOption.CREATE);
        Files.write(Paths.get("kind_m2m_structure.sql"), getKindStructureInserts(), StandardOpenOption.CREATE);
        Files.write(Paths.get("flk_type_control.sql"), getTypeControlInserts(), StandardOpenOption.CREATE);
        Files.write(Paths.get("kind_document.sql"), getKindDocumentInserts(), StandardOpenOption.CREATE);
    }



}

