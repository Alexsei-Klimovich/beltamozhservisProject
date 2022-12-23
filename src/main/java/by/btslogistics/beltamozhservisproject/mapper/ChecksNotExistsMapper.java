package by.btslogistics.beltamozhservisproject.mapper;

import by.btslogistics.beltamozhservisproject.dto.ChecksFromExcelDto;
import by.btslogistics.beltamozhservisproject.dto.ChecksNotExistDto;
import by.btslogistics.beltamozhservisproject.model.ChecksFromExcelModel;
import by.btslogistics.beltamozhservisproject.model.ChecksNotExistsModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ChecksNotExistsMapper {

    @Autowired
    ModelMapper mapper;

    public ChecksNotExistsModel toEntity(ChecksNotExistDto checksNotExistDto) {
        return Objects.isNull(checksNotExistDto) ? null : mapper.map(checksNotExistDto, ChecksNotExistsModel.class);
    }

    public ChecksNotExistDto toDto(ChecksNotExistsModel checksNotExistsModel) {
        return Objects.isNull(checksNotExistsModel) ? null : mapper.map(checksNotExistsModel, ChecksNotExistDto.class);
    }
}
