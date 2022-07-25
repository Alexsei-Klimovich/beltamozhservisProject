package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.exception.NotFoundException;
import by.btslogistics.beltamozhservisproject.model.KindStructure;
import by.btslogistics.beltamozhservisproject.repository.KindStructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 * @author Alexsei
 * @author Yaroslav
 *
 */
@Service
public class KindStructureService {
    @Autowired
    private KindStructureRepository kindStructureRepository;

    public void deleteKindStructureById(Long kindStructureId) {
        kindStructureRepository.deleteById(kindStructureId);
    }

    public KindStructure getKindStructureById (Long kindStructureId) {
        return kindStructureRepository.findById(kindStructureId).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void saveKindStructure(KindStructure kindStructure) {
        kindStructureRepository.save(kindStructure);
    }

    @Transactional
    public void updateKindStructure(KindStructure kindStructure) {
        kindStructureRepository.save(kindStructure);
    }

    public List<KindStructure> getAllKindStructures() {
        return kindStructureRepository.findAll();
    }
}
