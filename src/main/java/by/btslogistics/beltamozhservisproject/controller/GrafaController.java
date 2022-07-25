package by.btslogistics.beltamozhservisproject.controller;

import by.btslogistics.beltamozhservisproject.dto.GrafaDto;
import by.btslogistics.beltamozhservisproject.mapper.GrafaMapper;
import by.btslogistics.beltamozhservisproject.model.Grafa;
import by.btslogistics.beltamozhservisproject.service.GrafaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrafaController {
    @Autowired
    GrafaMapper grafaMapper;
    @Autowired
    GrafaService grafaService;
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
