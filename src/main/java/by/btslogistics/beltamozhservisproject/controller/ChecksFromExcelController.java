package by.btslogistics.beltamozhservisproject.controller;

import by.btslogistics.beltamozhservisproject.mapper.ChecksFromExcelMapper;
import by.btslogistics.beltamozhservisproject.service.ChecksFromExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChecksFromExcelController {

    @Autowired
    ChecksFromExcelMapper mapper;

    @Autowired
    ChecksFromExcelService service;
}
