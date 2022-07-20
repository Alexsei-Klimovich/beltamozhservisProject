package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.exception.NotFoundException;
import by.btslogistics.beltamozhservisproject.model.StructureDocument;
import by.btslogistics.beltamozhservisproject.repository.StructureDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Alexsei
 * @author Yaroslav
 */
@Service
public class StructureDocumentService {
    @Autowired
    private StructureDocumentRepository structureDocumentRepository;

    public void deleteDocumentById(Long documentId) {
        structureDocumentRepository.deleteById(documentId);
    }

    public StructureDocument getDocumentById(Long documentId) {
        return structureDocumentRepository.findById(documentId).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void saveStructureDocument(StructureDocument structureDocument) {
        structureDocumentRepository.save(structureDocument);
    }

    @Transactional
    public void updateStructureDocument(StructureDocument structureDocument) {
        structureDocumentRepository.save(structureDocument);
    }

    public StructureDocument getDocumentBySchemaName(String schemaName) {
        return structureDocumentRepository.getStructureDocumentBySchemaName(schemaName);
    }

    public List<StructureDocument> getAllStructureDocuments() {
        return structureDocumentRepository.findAll();
    }
}
