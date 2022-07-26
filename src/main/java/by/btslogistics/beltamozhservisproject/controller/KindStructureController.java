package by.btslogistics.beltamozhservisproject.controller;

import by.btslogistics.beltamozhservisproject.dto.KindStructureDto;
import by.btslogistics.beltamozhservisproject.mapper.KindStructureMapper;
import by.btslogistics.beltamozhservisproject.model.KindDocument;
import by.btslogistics.beltamozhservisproject.model.KindStructure;
import by.btslogistics.beltamozhservisproject.service.KindStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class KindStructureController {
    @Autowired
    KindStructureService kindStructureService;

    @Autowired
    KindStructureMapper kindStructureMapper;

    @GetMapping("/getKindStructure")
    public ResponseEntity<KindStructureDto> getKindStructure(@RequestParam("id") Long kindStructureId) {
        KindStructure kindStructure = kindStructureService.getKindStructureById(kindStructureId);
        KindStructureDto kindStructureDto = kindStructureMapper.toDto(kindStructure);
        if (kindStructure != null) {
            return new ResponseEntity<>(kindStructureDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/createKindStructure")
    public ResponseEntity<String> createKindStructure(@ModelAttribute KindStructure kindStructure) {
        kindStructureService.saveKindStructure(kindStructure);
        return new ResponseEntity<>("Saved", HttpStatus.OK);
    }

    @DeleteMapping("/deleteKindStructure")
    public ResponseEntity<String> deleteKindStructure(@RequestParam("id") Long kindStructureId) {
        kindStructureService.deleteKindStructureById(kindStructureId);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
