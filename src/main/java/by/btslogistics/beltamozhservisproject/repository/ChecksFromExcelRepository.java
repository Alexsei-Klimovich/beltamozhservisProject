package by.btslogistics.beltamozhservisproject.repository;

import by.btslogistics.beltamozhservisproject.model.ChecksFromExcelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChecksFromExcelRepository extends JpaRepository<ChecksFromExcelModel, String> {

}
