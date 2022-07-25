package by.btslogistics.beltamozhservisproject.controller;

import by.btslogistics.beltamozhservisproject.dto.CheckDto;
import by.btslogistics.beltamozhservisproject.dto.GrafaDto;
import by.btslogistics.beltamozhservisproject.dto.StructureDocumentDto;
import by.btslogistics.beltamozhservisproject.dto.TagDto;
import by.btslogistics.beltamozhservisproject.mapper.CheckMapper;
import by.btslogistics.beltamozhservisproject.mapper.StructureDocumentMapper;
import by.btslogistics.beltamozhservisproject.mapper.GrafaMapper;
import by.btslogistics.beltamozhservisproject.mapper.TagMapper;
import by.btslogistics.beltamozhservisproject.model.Check;
import by.btslogistics.beltamozhservisproject.model.Grafa;
import by.btslogistics.beltamozhservisproject.model.StructureDocument;
import by.btslogistics.beltamozhservisproject.model.Tag;
import by.btslogistics.beltamozhservisproject.service.CheckService;
import by.btslogistics.beltamozhservisproject.service.GrafaService;
import by.btslogistics.beltamozhservisproject.service.StructureDocumentService;
import by.btslogistics.beltamozhservisproject.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@Controller
public class ModelEditController {
    @Autowired
    StructureDocumentService structureDocumentService;
    @Autowired
    GrafaService grafaService;
    @Autowired
    CheckService checkService;
    @Autowired
    TagService tagService;
    @Autowired
    CheckMapper checkMapper;
    @Autowired
    TagMapper tagMapper;
    @Autowired
    StructureDocumentMapper structureDocumentMapper;
    @Autowired
    GrafaMapper grafaMapper;

    @GetMapping("/getCheck")
    public ResponseEntity<CheckDto> getCheckById(@RequestParam("id") String checkId) {
        Check check = checkService.getCheckById(Long.parseLong(checkId));
        CheckDto checkDto =checkMapper.toDto(check);
        if (check != null) {
            return new ResponseEntity<>(checkDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getTag")
    public ResponseEntity<TagDto> getTagById(@RequestParam("id") String tagId) {
        Tag tag = tagService.getTagById(Long.parseLong(tagId));
        TagDto tagDto =tagMapper.toDto(tag);
        if (tag != null) {
            return new ResponseEntity<>(tagDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getDocument")
    public ResponseEntity<StructureDocumentDto> getStructureDocumentById(@RequestParam("id") String documentId) {
        StructureDocument structureDocument = structureDocumentService.getDocumentById(Long.parseLong(documentId));
        StructureDocumentDto structureDocumentDto = structureDocumentMapper.toDto(structureDocument);
        if (structureDocumentDto != null) {
            return new ResponseEntity<>(structureDocumentDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getGrafa")
    public ResponseEntity<GrafaDto> getGrafaById(@RequestParam("id") String grafaId) {
        Grafa grafa = grafaService.getGrafaById(Long.parseLong(grafaId));
        GrafaDto grafaDto = grafaMapper.toDto(grafa);
        if (grafa != null) {
                return new ResponseEntity<>(grafaDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
