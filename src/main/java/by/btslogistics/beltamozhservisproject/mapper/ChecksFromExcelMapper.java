package by.btslogistics.beltamozhservisproject.mapper;

import by.btslogistics.beltamozhservisproject.dto.ChecksFromExcelDto;
import by.btslogistics.beltamozhservisproject.model.ChecksFromExcelModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ChecksFromExcelMapper {

    @Autowired
    ModelMapper mapper;

    public ChecksFromExcelModel toEntity(ChecksFromExcelDto checksFromExcelDto) {
        return Objects.isNull(checksFromExcelDto) ? null : mapper.map(checksFromExcelDto, ChecksFromExcelModel.class);
    }

    public ChecksFromExcelDto toDto(ChecksFromExcelModel checksFromExcelModel) {
        return Objects.isNull(checksFromExcelModel) ? null : mapper.map(checksFromExcelModel, ChecksFromExcelDto.class);
    }
}
