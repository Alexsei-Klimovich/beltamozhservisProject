package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.exception.NotFoundException;
import by.btslogistics.beltamozhservisproject.model.Grafa;
import by.btslogistics.beltamozhservisproject.repository.GrafaRepository;
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
public class GrafaService {
    @Autowired
    private GrafaRepository grafaRepository;



    public Grafa getGrafaByPathXml(String pathXml){
        return grafaRepository.getGrafaByPathXML(pathXml);
    }

    public void deleteGrafaById(Long grafaId) {
        grafaRepository.deleteById(grafaId);
    }

    public Grafa getGrafaById (Long grafaId) {
        return grafaRepository.findById(grafaId).orElseThrow(NotFoundException::new);
    }
    @Transactional
    public void saveGrafa(Grafa grafa) {
        grafaRepository.save(grafa);
    }
    @Transactional
    public void updateGrafa(Grafa grafa) {
        grafaRepository.save(grafa);
    }

    public List<Grafa> getAllGrafs() {
        return grafaRepository.findAll();
    }
}
