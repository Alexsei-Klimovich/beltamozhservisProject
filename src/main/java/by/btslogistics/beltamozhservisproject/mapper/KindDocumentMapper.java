package by.btslogistics.beltamozhservisproject.mapper;

import by.btslogistics.beltamozhservisproject.dto.KindDocumentDto;
import by.btslogistics.beltamozhservisproject.model.KindDocument;
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
public class KindDocumentMapper {
    @Autowired
    private ModelMapper modelMapper;

    public KindDocument toEntity(KindDocumentDto dto){
        return Objects.isNull(dto)? null: modelMapper.map(dto,KindDocument.class);
    }

    public KindDocumentDto toDto(KindDocument entity){
        return Objects.isNull(entity)? null: modelMapper.map(entity, KindDocumentDto.class);
    }
}
