package by.btslogistics.beltamozhservisproject.mapper;

import by.btslogistics.beltamozhservisproject.dto.StructureDocumentDto;
import by.btslogistics.beltamozhservisproject.model.StructureDocument;
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
public class StructureDocumentMapper {
    @Autowired
    private ModelMapper modelMapper;

    public StructureDocument toEntity(StructureDocumentDto dto){
        return Objects.isNull(dto)? null: modelMapper.map(dto, StructureDocument.class);
    }

    public StructureDocumentDto toDto(StructureDocument entity){
        return Objects.isNull(entity)? null: modelMapper.map(entity, StructureDocumentDto.class);
    }
}
