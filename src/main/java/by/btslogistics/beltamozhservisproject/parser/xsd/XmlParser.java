package by.btslogistics.beltamozhservisproject.parser.xsd;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class XmlParser {
    //TODO: MAKE RECURSION HERE
    public List<String> getChildrenPath() throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse("EEC_R_030_DeclarantNotification_v1.2.0.xsd.xml");
        // Получаем корневой элемент
        Node node = document.getDocumentElement();
        NodeList nodes = node.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node1 = nodes.item(i);
            if (nodes.item(i).getNodeType() != Node.TEXT_NODE && nodes.item(i).getNodeType() != Node.COMMENT_NODE) {
                System.out.println("1: /" + nodes.item(i).getNodeName());
            }
            NodeList nodes1 = node1.getChildNodes();
            for (int j = 0; j < nodes1.getLength(); j++) {
                Node node2 = nodes1.item(j);
                if (node2.getNodeType() != Node.TEXT_NODE && node2.getNodeType() != Node.COMMENT_NODE) {
                    System.out.println("2: /" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName());
                }
                NodeList nodes2 = node2.getChildNodes();
                for (int k = 0; k < nodes2.getLength(); k++) {
                    Node node3 = nodes2.item(k);
                    if (node3.getNodeType() != Node.TEXT_NODE && node3.getNodeType() != Node.COMMENT_NODE) {
                        System.out.println("3: /" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName() + "/" + nodes2.item(k).getNodeName());
                    }
                    NodeList nodes3 = node3.getChildNodes();
                    for (int m = 0; m < nodes3.getLength(); m++) {
                        Node node4 = nodes3.item(m);
                        if (node4.getNodeType() != Node.TEXT_NODE && node4.getNodeType() != Node.COMMENT_NODE) {
                            System.out.println("4: /" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName() + "/" + nodes2.item(k).getNodeName() + "/" + nodes3.item(m).getNodeName());
                        }
                        NodeList nodes4 = node4.getChildNodes();
                        for (int n = 0; n < nodes4.getLength(); n++) {
                            Node node5 = nodes4.item(n);
                            if (node5.getNodeType() != Node.TEXT_NODE && node5.getNodeType() != Node.COMMENT_NODE) {
                                System.out.println("5: /" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName() + "/" + nodes2.item(k).getNodeName() + "/" + nodes3.item(m).getNodeName() + "/" + nodes4.item(n).getNodeName());
                            }
                        }
                    }
                }


            }
        }
        return null;
    }
}









