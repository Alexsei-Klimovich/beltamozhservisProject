package by.btslogistics.beltamozhservisproject.mapper;

import by.btslogistics.beltamozhservisproject.dto.KindStructureDto;
import by.btslogistics.beltamozhservisproject.model.KindStructure;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 *
 * @author Alexsei
 * @author Yaroslav
 *
 */
@Component
public class KindStructureMapper {
    @Autowired
    private ModelMapper modelMapper;

    public KindStructure toEntity(KindStructureDto dto){
        return Objects.isNull(dto)? null: modelMapper.map(dto,KindStructure.class);
    }

    public KindStructureDto toDto(KindStructure entity){
        return Objects.isNull(entity)? null: modelMapper.map(entity, KindStructureDto.class);
    }
}
