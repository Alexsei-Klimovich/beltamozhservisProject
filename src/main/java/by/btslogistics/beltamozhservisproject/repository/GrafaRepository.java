package by.btslogistics.beltamozhservisproject.repository;

import by.btslogistics.beltamozhservisproject.model.Grafa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrafaRepository extends JpaRepository<Grafa, Long> {

}
