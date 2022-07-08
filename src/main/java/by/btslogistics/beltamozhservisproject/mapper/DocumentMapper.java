package by.btslogistics.beltamozhservisproject.mapper;

import by.btslogistics.beltamozhservisproject.dto.CheckDto;
import by.btslogistics.beltamozhservisproject.dto.DocumentDto;
import by.btslogistics.beltamozhservisproject.model.Check;
import by.btslogistics.beltamozhservisproject.model.Document;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
/**
 *
 * @author Alexsei
 * @author Yaroslav
 *
 */
public class DocumentMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Document toEntity(DocumentDto dto){
        return Objects.isNull(dto)? null: modelMapper.map(dto,Document.class);
    }

    public DocumentDto toDto(Document entity){
        return Objects.isNull(entity)? null: modelMapper.map(entity, DocumentDto.class);
    }
}
