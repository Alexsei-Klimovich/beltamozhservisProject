package by.btslogistics.beltamozhservisproject.repository;

import by.btslogistics.beltamozhservisproject.model.KindDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alexsei
 * @author Yaroslav
 *
 */
@Repository
public interface KindDocumentRepository extends JpaRepository<KindDocument, Long> {

}
