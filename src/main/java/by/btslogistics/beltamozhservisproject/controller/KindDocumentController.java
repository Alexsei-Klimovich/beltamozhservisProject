package by.btslogistics.beltamozhservisproject.controller;

import by.btslogistics.beltamozhservisproject.dto.KindDocumentDto;
import by.btslogistics.beltamozhservisproject.mapper.KindDocumentMapper;
import by.btslogistics.beltamozhservisproject.mapper.KindStructureMapper;
import by.btslogistics.beltamozhservisproject.model.KindDocument;
import by.btslogistics.beltamozhservisproject.service.KindDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class KindDocumentController {
    @Autowired
    KindDocumentService kindDocumentService;

    @Autowired
    KindDocumentMapper kindDocumentMapper;

    @GetMapping("/getKindDocument")
    public ResponseEntity<KindDocumentDto> getKindDocument(@RequestParam("id") Long kindDocumentId) {
        KindDocument kindDocument = kindDocumentService.getKindDocumentById(kindDocumentId);
        if (kindDocument != null) {
            return new ResponseEntity<>(kindDocumentMapper.toDto(kindDocument), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/createKindDocument")
    public ResponseEntity<String> createKindDocument(@ModelAttribute KindDocument kindDocumentId) {
        kindDocumentService.saveKindDocument(kindDocumentId);
        return new ResponseEntity<>("Saved", HttpStatus.OK);
    }

    @DeleteMapping("/deleteKindDocument")
    public ResponseEntity<String> deleteKindDocument(@RequestParam("id") Long kindDocumentId) {
        kindDocumentService.deleteKindDocumentById(kindDocumentId);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
