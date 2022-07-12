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

    private static final String FILE_NAME = "EEC_M_CA_SimpleDataObjects_vbts2.xsd";

    public static void parseXsd(){
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File(FILE_NAME));
            printXsdInfo(doc);

        }
        catch (ParserConfigurationException | SAXException | IOException e)
        {
            e.printStackTrace();
        }
    }


    public static void printXsdInfo( Document document){
        NodeList documentations = document.getElementsByTagName("xs:documentation");
        System.out.println("__________________________________");
        for(int i = 0 ; i < documentations.getLength(); i++)
        {
            Element element = (Element)documentations.item(i);
            Element parentElement = (Element) element.getParentNode().getParentNode();
            if(parentElement.getAttribute("name").isEmpty()){
                Element doubleParentNode = (Element) parentElement.getParentNode().getParentNode();
                System.out.println("name: "+doubleParentNode.getAttribute("name"));
                System.out.println("type: "+doubleParentNode.getAttribute("type"));
                System.out.println("parent: "+parentElement.getParentNode().getParentNode().getNodeName());
            } else{
                System.out.println("name: "+parentElement.getAttribute("name"));
                System.out.println("type: "+parentElement.getAttribute("type"));
                System.out.println("parent: "+element.getParentNode().getParentNode().getNodeName());
            }
            System.out.println("documentation: "+element.getTextContent());
            System.out.println("__________________________________");
        }
    }


}
