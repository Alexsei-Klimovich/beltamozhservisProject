package by.btslogistics.beltamozhservisproject.mapper;

import by.btslogistics.beltamozhservisproject.dto.CheckDto;
import by.btslogistics.beltamozhservisproject.model.Check;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;
/**
 *
 * @author Alexsei
 * @author Yaroslav
 *
 */
@Component
public class CheckMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Check toEntity(CheckDto dto){
        return Objects.isNull(dto)? null: modelMapper.map(dto,Check.class);
    }

    public CheckDto toDto(Check entity){
        return Objects.isNull(entity)? null: modelMapper.map(entity, CheckDto.class);
    }
}
