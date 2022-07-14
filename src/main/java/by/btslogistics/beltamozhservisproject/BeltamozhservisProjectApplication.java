package by.btslogistics.beltamozhservisproject;


import by.btslogistics.beltamozhservisproject.parser.xsd.XsdParser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.xml.sax.SAXException;

import javax.servlet.MultipartConfigElement;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;
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

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        SpringApplication.run(BeltamozhservisProjectApplication.class, args);
//        XsdParser xsdParser = new XsdParser();
//        xsdParser.printRootXsdInfo(new File("EEC_R_030_DeclarantNotification_v1.2.0.xsd"));
    }

}
