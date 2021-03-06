package by.btslogistics.beltamozhservisproject.parser.xsd;

import by.btslogistics.beltamozhservisproject.service.XsdService;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class XmlParser {
    //TODO: MAKE RECURSION HERE
    private final File rootXml;

    public XmlParser(File rootXml) {
        this.rootXml = rootXml;
    }

    public List<String> getChildrenPath() throws IOException, SAXException, ParserConfigurationException {
        List<String> paths = new ArrayList<>();
        Document document = XsdParser.buildDocumentFromFile(rootXml);
        // Получаем корневой элемент
        Node node = document.getDocumentElement();
        NodeList nodes = node.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node1 = nodes.item(i);
            if (nodes.item(i).getNodeType() != Node.TEXT_NODE && nodes.item(i).getNodeType() != Node.COMMENT_NODE) {
                paths.add("/" + nodes.item(i).getNodeName());
            }
            NodeList nodes1 = node1.getChildNodes();
            for (int j = 0; j < nodes1.getLength(); j++) {
                Node node2 = nodes1.item(j);
                if (node2.getNodeType() != Node.TEXT_NODE && node2.getNodeType() != Node.COMMENT_NODE) {
                    paths.add("/" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName());
                }
                NodeList nodes2 = node2.getChildNodes();
                for (int k = 0; k < nodes2.getLength(); k++) {
                    Node node3 = nodes2.item(k);
                    if (node3.getNodeType() != Node.TEXT_NODE && node3.getNodeType() != Node.COMMENT_NODE) {
                        paths.add("/" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName() + "/" + nodes2.item(k).getNodeName());
                    }
                    NodeList nodes3 = node3.getChildNodes();
                    for (int m = 0; m < nodes3.getLength(); m++) {
                        Node node4 = nodes3.item(m);
                        if (node4.getNodeType() != Node.TEXT_NODE && node4.getNodeType() != Node.COMMENT_NODE) {
                            paths.add("/" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName() + "/" +
                                    nodes2.item(k).getNodeName() + "/" + nodes3.item(m).getNodeName());
                        }
                        NodeList nodes4 = node4.getChildNodes();
                        for (int n = 0; n < nodes4.getLength(); n++) {
                            Node node5 = nodes4.item(n);
                            if (node5.getNodeType() != Node.TEXT_NODE && node5.getNodeType() != Node.COMMENT_NODE) {
                                paths.add("/" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName() + "/" +
                                        nodes2.item(k).getNodeName() + "/" + nodes3.item(m).getNodeName() + "/" + nodes4.item(n).getNodeName());
                            }
                            NodeList nodes5 = node5.getChildNodes();
                            for (int b = 0; b < nodes5.getLength(); b++) {
                                Node node6 = nodes5.item(b);
                                if (node6.getNodeType() != Node.TEXT_NODE && node6.getNodeType() != Node.COMMENT_NODE) {
                                    paths.add("/" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName() + "/" +
                                            nodes2.item(k).getNodeName() + "/" + nodes3.item(m).getNodeName() + "/" + nodes4.item(n).getNodeName() + "/" + nodes5.item(b).getNodeName());
                                }
                                NodeList nodes6 = node6.getChildNodes();
                                for (int a = 0; a < nodes6.getLength(); a++) {
                                    Node node7 = nodes6.item(a);
                                    if (node7.getNodeType() != Node.TEXT_NODE && node7.getNodeType() != Node.COMMENT_NODE) {
                                        paths.add("/" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName() + "/" +
                                                nodes2.item(k).getNodeName() + "/" + nodes3.item(m).getNodeName() + "/" + nodes4.item(n).getNodeName() + "/" + nodes5.item(b).getNodeName()+"/" + nodes6.item(a).getNodeName());
                                    }
                                    NodeList nodes7 = node7.getChildNodes();
                                    for (int c = 0; c < nodes7.getLength(); c++) {
                                        Node node8 = nodes7.item(c);
                                        if (node8.getNodeType() != Node.TEXT_NODE && node8.getNodeType() != Node.COMMENT_NODE) {
                                            paths.add("/" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName() + "/" +
                                                    nodes2.item(k).getNodeName() + "/" + nodes3.item(m).getNodeName() + "/" + nodes4.item(n).getNodeName() + "/" + nodes5.item(b).getNodeName() + "/" + nodes6.item(a).getNodeName()+"/" + nodes7.item(c).getNodeName());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return changePathsPrefix(paths);
    }

    //TODO: REFACTOR THIS
    public List<String> changePathsPrefix(List<String> paths) throws IOException, ParserConfigurationException, SAXException {
        System.out.println("LLLLLL:" + paths.size());
        Document document = XsdParser.buildDocumentFromFile(rootXml);
        Node node = document.getDocumentElement();
        NamedNodeMap tags = node.getAttributes();
        Map<String, String> prefixMap = new HashMap<>();
        for (int i = 0; i < tags.getLength(); i++) {
            if (tags.item(i).toString().contains("xmlns")) {
                List<String> splitedTags = List.of(tags.item(i).toString().split("="));
                String fileName = List.of(tags.item(i).toString().split("\"")).get(1).toString().
                        replace(":", "_").replace("urn_", "") + ".xsd";
                String oldPrefix = splitedTags.get(0).replace("xmlns:", "");
                prefixMap.put(oldPrefix, fileName);
            }
        }
        prefixMap.remove("urn");
        Map<String, String> newPrefixMap = new HashMap<>();
        List<String> keys = new ArrayList<>();
        for (Map.Entry<String, String> entry : prefixMap.entrySet()) {
            keys.add(entry.getKey());
            newPrefixMap.put(entry.getKey(), XsdService.getPathPrefixFromFile(new File(entry.getValue())));
        }
        List<String> changesPrefixPaths = new ArrayList<>();
        for (String path : paths) {
            List<String> splitedPath = List.of(path.split("/"));
            String result = "";
            for (String split : splitedPath) {
                for (String key : keys) {
                    if (split.contains(key)) {
                        result = result + split.replace(key, newPrefixMap.get(key));
                    }
                }
            }
            String rootNodeName = List.of(node.getNodeName().split(":")).get(1);
            String rootPrefix = XsdService.getPathPrefixFromFile(new File(rootXml.getName().replace(".xml", "")));
            changesPrefixPaths.add(rootPrefix + rootNodeName + result.replace("::", ":"));

        }
        System.out.println("SIZE2:" + changesPrefixPaths.size());
        return changesPrefixPaths;
    }

    public Map<String, String> getElementsPathMap(List<String> elementsName, List<String> elementsPaths) throws IOException, ParserConfigurationException, SAXException {
        Map<String, String> elementsPathMap = new HashMap<>();
        elementsName.remove(getRootElementName());
        for (String elementName : elementsName) {
            for (String elementPath : elementsPaths) {
                List<String> splitedPath = List.of(elementPath.split("/"));
                String lastPathElement = splitedPath.get(splitedPath.size() - 1);
                if (lastPathElement.contains(elementName)) {
                    elementsPathMap.put(elementName, elementPath);
                }
            }
        }
        return elementsPathMap;
    }

    public List<String> getElementsName(List<String> elementsPaths) throws IOException, ParserConfigurationException, SAXException {

        List<String> elements = new ArrayList<>();
        for (String elementPath : elementsPaths) {
            List<String> splitedElementPath = List.of(elementPath.split("/"));
            elements.add(List.of(splitedElementPath.get(splitedElementPath.size() - 1).split(":")).get(1));
        }
        elements.add(getRootElementName());
        return elements;
    }

    public String getRootElementName() throws IOException, ParserConfigurationException, SAXException {
        Document document = XsdParser.buildDocumentFromFile(rootXml);
        Element node = document.getDocumentElement();
        return List.of(node.getNodeName().split(":")).get(1);
    }

    public String getRootElementPath() throws IOException, ParserConfigurationException, SAXException {
        return XsdService.getPathPrefixFromFile(new File(rootXml.getName().replace(".xml", ""))) + getRootElementName();
    }

    public Map<String, String> getElementsDocumentationMap(List<String> elements) throws IOException, ParserConfigurationException, SAXException {
        Map<String, String> documentationForElement = new HashMap<>();
        List<String> fileNames = getFileNames();
        for (String fileName : fileNames) {
            Document document = XsdParser.buildDocumentFromFile(new File(fileName));
            NodeList documentations = document.getElementsByTagName("xs:documentation");
            for (int i = 0; i < documentations.getLength(); i++) {
                Element element = (Element) documentations.item(i);
                Element parentElement = (Element) element.getParentNode().getParentNode();
                String attributeName = parentElement.getAttribute("name");
                for (String elementName : elements) {
                    if (elementName.equals(attributeName)) {
                        documentationForElement.put(attributeName, element.getTextContent());
                    }
                }
            }
        }
        return documentationForElement;
    }

    public List<String> getFileNames() throws IOException, ParserConfigurationException, SAXException {
        List<String> fileNames = new ArrayList<>();
        Document document = XsdParser.buildDocumentFromFile(rootXml);
        Node node = document.getDocumentElement();
        NamedNodeMap tags = node.getAttributes();
        for (int i = 0; i < tags.getLength(); i++) {
            if (tags.item(i).toString().contains("xmlns")) {
                String fileName = List.of(tags.item(i).toString().split("\"")).get(1).toString().
                        replace(":", "_").replace("urn_", "") + ".xsd";
                fileNames.add(fileName);
            }
        }
        return fileNames;
    }

    public String getParentElementPath(String elementPath) {
        String parentElementPath = "";
        List<String> splitedPath = List.of(elementPath.split("/"));
        for (int i = 0; i < splitedPath.size() - 1; i++) {
            parentElementPath = parentElementPath + splitedPath.get(i) + "/";
        }
        parentElementPath = parentElementPath.substring(0, parentElementPath.length() - 1);
        return parentElementPath;
    }

    public Map<String, String> getAllPatternsMap() throws IOException, ParserConfigurationException, SAXException {
        Map<String, String> patternsMap = new HashMap<>();
        List<String> fileNames = getFileNames();
        for (String fileName : fileNames) {
            Document document = XsdParser.buildDocumentFromFile(new File(fileName));
            NodeList patterns = document.getElementsByTagName("xs:pattern");
            for (int i = 0; i < patterns.getLength(); i++) {
                Element element = (Element) patterns.item(i);
                String pattern = element.getAttribute("value");
                Element parentElement = (Element) element.getParentNode().getParentNode();
                String attributeName = parentElement.getAttribute("name").replace("Type", "");
                if (attributeName.isEmpty()) {
                    Element doubleParent = (Element) parentElement.getParentNode();
                    attributeName = doubleParent.getAttribute("name").replace("Type", "");
                }
                patternsMap.put(attributeName, pattern);
            }
        }
        return patternsMap;
    }

    public String getPatternForElement(String elementName) throws IOException, ParserConfigurationException, SAXException {
        Map<String, String> patterns = getAllPatternsMap();
        String pattern = null;
        pattern = patterns.get(elementName);
        return pattern;
    }


}









