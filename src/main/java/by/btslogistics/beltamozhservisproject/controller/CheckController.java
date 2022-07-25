package by.btslogistics.beltamozhservisproject.controller;

import by.btslogistics.beltamozhservisproject.dto.CheckDto;
import by.btslogistics.beltamozhservisproject.mapper.CheckMapper;
import by.btslogistics.beltamozhservisproject.model.Check;
import by.btslogistics.beltamozhservisproject.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {
    @Autowired
    CheckMapper checkMapper;
    @Autowired
    CheckService checkService;

    @GetMapping("/getCheck")
    public ResponseEntity<CheckDto> getCheckById(@RequestParam("id") String checkId) {
        Check check = checkService.getCheckById(Long.parseLong(checkId));
        CheckDto checkDto =checkMapper.toDto(check);
        if (check != null) {
            return new ResponseEntity<>(checkDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteCheck")
    public ResponseEntity<String> deleteCheck(@RequestParam("id") String checkId) {
        checkService.deleteCheckById(Long.parseLong(checkId));
        return new ResponseEntity<>("Check deleted", HttpStatus.OK);
    }
}
