package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.model.ChecksNotExistsModel;
import by.btslogistics.beltamozhservisproject.repository.ChecksNotExistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class ChecksNotExistsService {

    @Autowired
    private ChecksNotExistsRepository repository;

    @Transactional
    public void saveCheck(ChecksNotExistsModel check) {
        repository.save(check);
    }

    public List<ChecksNotExistsModel> getAll() {
        return repository.findAll();
    }

}
