package by.btslogistics.beltamozhservisproject.parser.xsd;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XsdParser {

    private static final String FILE_NAME = "EEC_M_BaseDataTypes_vbts6.xsd";

    public static void parseXsd(){
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File(FILE_NAME));
            printXsdInfo("xs:element",doc);
            printXsdInfo("xs:simpleType",doc);
            printXsdInfo("xs:complexType",doc);

        }
        catch (ParserConfigurationException | SAXException | IOException e)
        {
            e.printStackTrace();
        }
    }


    public static void printXsdInfo(String elementTagName, Document document){
        NodeList elements = document.getElementsByTagName(elementTagName);
        for(int i = 0 ; i < elements.getLength(); i++)
        {
            Element element = (Element)elements.item(i);
            if(element.hasAttributes())
            {
                System.out.println("name: "+ element.getAttribute("name")) ;
                System.out.println("type: "+ element.getAttribute("type")) ;
                System.out.println("text content: "+ element.getTextContent()) ;
            }
        }
    }


}
