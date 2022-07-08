package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.model.Check;
import by.btslogistics.beltamozhservisproject.model.Document;
import by.btslogistics.beltamozhservisproject.model.Grafa;
import by.btslogistics.beltamozhservisproject.repository.DocumentRepository;
import by.btslogistics.beltamozhservisproject.repository.GrafaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrafaService {
    @Autowired
    private GrafaRepository grafaRepository;

    public void deleteGrafaById(Long grafaId) {
        grafaRepository.deleteById(grafaId);
    }

    public Grafa getGrafaById (Long grafaId) {
        return grafaRepository.getById(grafaId);
    }

    public void saveGrafa(Grafa grafa) {
        grafaRepository.save(grafa);
    }

    public void updateGrafa(Grafa grafa) {
        grafaRepository.save(grafa);
    }

    public List<Grafa> getAllGrafs() {
        return grafaRepository.findAll();
    }
}
