package by.btslogistics.beltamozhservisproject.repository;

import by.btslogistics.beltamozhservisproject.model.Check;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Alexsei
 * @author Yaroslav
 *
 */
@Repository
public interface CheckRepository extends JpaRepository<Check, Long> {

    @Query(value = "select to_tag_doc_id  from flk_checks where id=?1",nativeQuery = true)
    Long getToStrDocIdByCheckId(Long checkId);
}
