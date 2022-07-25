package by.btslogistics.beltamozhservisproject.repository;

import by.btslogistics.beltamozhservisproject.model.StructureDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Alexsei
 * @author Yaroslav
 *
 */
@Repository
public interface StructureDocumentRepository extends JpaRepository<StructureDocument, Long> {
    StructureDocument getStructureDocumentBySchemaName(String schemaName);
    StructureDocument getStructureDocumentBySchemaLocation(String schemaLocation);
}
