package by.btslogistics.beltamozhservisproject;


import by.btslogistics.beltamozhservisproject.parser.xsd.XsdParser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

import java.util.List;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

/**
 *
 * @author Alexsei
 * @author Yaroslav
 *
 */
@SpringBootApplication
public class BeltamozhservisProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeltamozhservisProjectApplication.class, args);
    }

}
