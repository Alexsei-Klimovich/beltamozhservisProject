package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.model.Check;
import by.btslogistics.beltamozhservisproject.model.Document;
import by.btslogistics.beltamozhservisproject.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public void deleteDocumentById(Long documentId) {
        documentRepository.deleteById(documentId);
    }

    public Document getDocumentById (Long documentId) {
        return documentRepository.getById(documentId);
    }
    @Transactional
    public void saveDocument(Document document) {
        documentRepository.save(document);
    }
    @Transactional
    public void updateDocument(Document document) {
        documentRepository.save(document);
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }
}
