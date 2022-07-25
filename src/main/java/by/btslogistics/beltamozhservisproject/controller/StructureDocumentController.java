package by.btslogistics.beltamozhservisproject.controller;

import by.btslogistics.beltamozhservisproject.dto.StructureDocumentDto;
import by.btslogistics.beltamozhservisproject.mapper.StructureDocumentMapper;
import by.btslogistics.beltamozhservisproject.model.StructureDocument;
import by.btslogistics.beltamozhservisproject.service.StructureDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StructureDocumentController {
    @Autowired
    StructureDocumentService structureDocumentService;
    @Autowired
    StructureDocumentMapper structureDocumentMapper;

    @GetMapping("/getDocument")
    public ResponseEntity<StructureDocumentDto> getStructureDocumentById(@RequestParam("id") String documentId) {
        StructureDocument structureDocument = structureDocumentService.getDocumentById(Long.parseLong(documentId));
        StructureDocumentDto structureDocumentDto = structureDocumentMapper.toDto(structureDocument);
        if (structureDocumentDto != null) {
            return new ResponseEntity<>(structureDocumentDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStructureDocument(@RequestParam("id") String documentId) {
        structureDocumentService.deleteDocumentById(Long.parseLong(documentId));
        return new ResponseEntity<>("Document deleted", HttpStatus.OK);
    }
}
