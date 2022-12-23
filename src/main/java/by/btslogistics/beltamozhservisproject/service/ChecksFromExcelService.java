package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.repository.ChecksFromExcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChecksFromExcelService {

    @Autowired
    private ChecksFromExcelRepository repository;
}
