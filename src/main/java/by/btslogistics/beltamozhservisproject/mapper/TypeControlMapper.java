package by.btslogistics.beltamozhservisproject.mapper;

import by.btslogistics.beltamozhservisproject.dto.TypeControlDto;
import by.btslogistics.beltamozhservisproject.model.TypeControl;
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
public class TypeControlMapper {
    @Autowired
    private ModelMapper modelMapper;

    public TypeControl toEntity(TypeControlDto dto){
        return Objects.isNull(dto)? null: modelMapper.map(dto,TypeControl.class);
    }

    public TypeControlDto toDto(TypeControl entity){
        return Objects.isNull(entity)? null: modelMapper.map(entity, TypeControlDto.class);
    }
}
