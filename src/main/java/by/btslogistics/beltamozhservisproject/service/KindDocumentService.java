package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.exception.NotFoundException;
import by.btslogistics.beltamozhservisproject.model.KindDocument;
import by.btslogistics.beltamozhservisproject.repository.KindDocumentRepository;
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
public class KindDocumentService {
    @Autowired
    private KindDocumentRepository kindDocumentRepository;

    public void deleteKindDocumentById(Long kindDocumentId) {
        kindDocumentRepository.deleteById(kindDocumentId);
    }

    public KindDocument getKindDocumentById (Long kindDocumentId) {
        return kindDocumentRepository.findById(kindDocumentId).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void saveKindDocument(KindDocument kindDocument) {
        kindDocumentRepository.save(kindDocument);
    }

    @Transactional
    public void updateKindDocument(KindDocument kindDocument) {
        kindDocumentRepository.save(kindDocument);
    }

    public List<KindDocument> getAllKindDocuments() {
        return kindDocumentRepository.findAll();
    }
}
