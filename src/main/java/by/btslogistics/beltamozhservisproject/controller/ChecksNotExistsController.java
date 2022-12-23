package by.btslogistics.beltamozhservisproject.controller;

import by.btslogistics.beltamozhservisproject.mapper.ChecksNotExistsMapper;
import by.btslogistics.beltamozhservisproject.service.ChecksNotExistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChecksNotExistsController {

    @Autowired
    ChecksNotExistsMapper mapper;

    @Autowired
    ChecksNotExistsService service;

}
