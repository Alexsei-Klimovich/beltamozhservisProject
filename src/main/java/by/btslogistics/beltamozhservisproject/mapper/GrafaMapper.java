package by.btslogistics.beltamozhservisproject.mapper;

import by.btslogistics.beltamozhservisproject.dto.CheckDto;
import by.btslogistics.beltamozhservisproject.dto.GrafaDto;
import by.btslogistics.beltamozhservisproject.model.Check;
import by.btslogistics.beltamozhservisproject.model.Grafa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GrafaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Grafa toEntity(CheckDto dto){
        return Objects.isNull(dto)? null: modelMapper.map(dto,Grafa.class);
    }

    public GrafaDto toDto(Check entity){
        return Objects.isNull(entity)? null: modelMapper.map(entity, GrafaDto.class);
    }
}
