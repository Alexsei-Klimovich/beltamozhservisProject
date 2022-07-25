package by.btslogistics.beltamozhservisproject.repository;

import by.btslogistics.beltamozhservisproject.model.KindStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alexsei
 * @author Yaroslav
 *
 */
@Repository
public interface KindStructureRepository extends JpaRepository<KindStructure, Long> {

}
