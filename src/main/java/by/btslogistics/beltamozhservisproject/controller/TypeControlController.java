package by.btslogistics.beltamozhservisproject.controller;

import by.btslogistics.beltamozhservisproject.dto.CheckDto;
import by.btslogistics.beltamozhservisproject.dto.TagDto;
import by.btslogistics.beltamozhservisproject.dto.TypeControlDto;
import by.btslogistics.beltamozhservisproject.mapper.TypeControlMapper;
import by.btslogistics.beltamozhservisproject.model.Check;
import by.btslogistics.beltamozhservisproject.model.Tag;
import by.btslogistics.beltamozhservisproject.model.TypeControl;
import by.btslogistics.beltamozhservisproject.service.TypeControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TypeControlController {
    @Autowired
    TypeControlService typeControlService;

    @Autowired
    TypeControlMapper typeControlMapper;


    @GetMapping("/getTypeControl")
    public ResponseEntity<TypeControlDto> getTypeControl(@RequestParam("id") Long typeControlId) {
        TypeControl typeControl = typeControlService.getTypeControlById(typeControlId);
        if (typeControl != null) {
            return new ResponseEntity<>(typeControlMapper.toDto(typeControl), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/createTypeControl")
    public ResponseEntity<TypeControl> createTypeControl(@ModelAttribute TypeControl typeControlId) {
        typeControlService.saveTypeControl(typeControlId);
        return new ResponseEntity<>(typeControlId, HttpStatus.OK);
    }

    @DeleteMapping("/deleteTypeControl")
    public ResponseEntity<String> deleteTypeControl(@RequestParam("id") Long id) {
        typeControlService.deleteTypeControlById(id);
        return new ResponseEntity<>("TypeControl deleted", HttpStatus.OK);
    }
}
