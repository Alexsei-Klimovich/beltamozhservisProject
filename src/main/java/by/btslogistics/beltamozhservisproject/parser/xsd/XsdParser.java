package by.btslogistics.beltamozhservisproject.parser.xsd;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;


public class XsdParser {


//    public void parseRootXsd(File file) throws IOException, ParserConfigurationException, SAXException {
//        printXsdInfo(buildDocumentFromFile(file));
//    }



    public static Document buildDocumentFromFile(File file) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        return docBuilder.parse(file);
    }


//    public static void parseXsd(File file) {
//        try {
//            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
//            Document doc = docBuilder.parse(file);
//            printXsdInfo(doc);
//
//        } catch (ParserConfigurationException | SAXException | IOException e) {
//            e.printStackTrace();
//        }
//    }




}
