package by.btslogistics.beltamozhservisproject.controller;

import by.btslogistics.beltamozhservisproject.dto.GrafaDto;
import by.btslogistics.beltamozhservisproject.mapper.GrafaMapper;
import by.btslogistics.beltamozhservisproject.model.Grafa;
import by.btslogistics.beltamozhservisproject.model.Tag;
import by.btslogistics.beltamozhservisproject.service.GrafaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    //получить всю таблицу графа
    @GetMapping("/getAllGrafa")
    public ResponseEntity<List<GrafaDto>> getAllGrafs(){
        List<Grafa> grafaList = grafaService.getAllGrafs();
        List<GrafaDto> grafaDtoList = new ArrayList<>();
        for(var grafa : grafaList){
            grafaDtoList.add(grafaMapper.toDto(grafa));
        }
        return new ResponseEntity<>(grafaDtoList,HttpStatus.OK);
    }
    @PostMapping("/createGrafa")
    public ResponseEntity<String> createGrafa(@ModelAttribute("grafa") Grafa grafa){
        grafaService.saveGrafa(grafa);
        return new ResponseEntity<>("Saved",HttpStatus.OK);
    }

    @DeleteMapping("/deleteGrafa")
    public ResponseEntity<String> deleteGrafa(@RequestParam("id") String grafaId) {
        grafaService.deleteGrafaById(Long.parseLong(grafaId));
        return new ResponseEntity<>("Grafa deleted", HttpStatus.OK);
    }


}
