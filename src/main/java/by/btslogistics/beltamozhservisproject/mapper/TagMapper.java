package by.btslogistics.beltamozhservisproject.mapper;

import by.btslogistics.beltamozhservisproject.dto.CheckDto;
import by.btslogistics.beltamozhservisproject.dto.TagDto;
import by.btslogistics.beltamozhservisproject.model.Check;
import by.btslogistics.beltamozhservisproject.model.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Objects;
/**
 *
 * @author Alexsei
 * @author Yaroslav
 *
 */
@Component
public class TagMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Tag toEntity(TagDto dto){
        return Objects.isNull(dto)? null: modelMapper.map(dto, Tag.class);
    }

    public TagDto toDto(Tag entity){
        return Objects.isNull(entity)? null: modelMapper.map(entity, TagDto.class);
    }
}
