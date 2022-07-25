package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.exception.NotFoundException;
import by.btslogistics.beltamozhservisproject.model.TypeControl;
import by.btslogistics.beltamozhservisproject.repository.TypeControlRepository;
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
public class TypeControlService {
    @Autowired
    private TypeControlRepository typeControlRepository;

    public void deleteTypeControlById(Long typeControlId) {
        typeControlRepository.deleteById(typeControlId);
    }

    public TypeControl getTypeControlById (Long typeControlId) {
        return typeControlRepository.findById(typeControlId).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void saveTypeControl(TypeControl typeControl) {
        typeControlRepository.save(typeControl);
    }

    @Transactional
    public void updateTypeControl(TypeControl typeControl) {
        typeControlRepository.save(typeControl);
    }

    public List<TypeControl> getAllTypeControls() {
        return typeControlRepository.findAll();
    }
}
