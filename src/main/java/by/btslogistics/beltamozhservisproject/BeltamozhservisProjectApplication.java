package by.btslogistics.beltamozhservisproject;


import by.btslogistics.beltamozhservisproject.parser.xsd.XmlParser;
import by.btslogistics.beltamozhservisproject.service.XmlService;
import by.btslogistics.beltamozhservisproject.service.XsdService;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Map;

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
