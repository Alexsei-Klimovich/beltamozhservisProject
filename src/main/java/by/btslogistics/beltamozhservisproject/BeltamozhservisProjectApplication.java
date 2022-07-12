package by.btslogistics.beltamozhservisproject;

import by.btslogistics.beltamozhservisproject.parser.excel.ExcelParse;
import by.btslogistics.beltamozhservisproject.parser.xsd.XsdParser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

/**
 *
 * @author Alexsei
 * @author Yaroslav
 *
 */
@SpringBootApplication
public class BeltamozhservisProjectApplication {

    //TODO: REPLACE THIS
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        return mapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(BeltamozhservisProjectApplication.class, args);
        ExcelParse excelParse = new ExcelParse();
        excelParse.excelParse();
        XsdParser.parseXsd();
    }

}
