package by.btslogistics.beltamozhservisproject.parser.xsd;

import by.btslogistics.beltamozhservisproject.service.XsdService;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static by.btslogistics.beltamozhservisproject.parser.xsd.XsdParser.buildDocumentFromFile;

public class XmlParser {

    private final File rootXml;

    public XmlParser(File rootXml) {
        this.rootXml = rootXml;
    }
    //TODO: MAKE RECURSION HERE
    public List<String> getChildrenPath() throws IOException, SAXException, ParserConfigurationException {
        List<String> paths = new ArrayList<>();
        Document document = XsdParser.buildDocumentFromFile(rootXml);
        Node node = document.getDocumentElement();
        NodeList nodes = node.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node1 = nodes.item(i);
            if (nodes.item(i).getNodeType() != Node.COMMENT_NODE && !nodes.item(i).getNodeName().contains("#text")) {
                paths.add("/" + nodes.item(i).getNodeName());
                if (nodes.item(i).hasAttributes()) {
                    for (int j = 0; j < nodes.item(i).getAttributes().getLength(); j++) {
                        paths.add("/" + nodes.item(i).getNodeName() + "/@" + nodes.item(i).getAttributes().item(j).getNodeName());
                    }
                }
            }
            NodeList nodes1 = node1.getChildNodes();
            for (int j = 0; j < nodes1.getLength(); j++) {
                Node node2 = nodes1.item(j);
                if ( node2.getNodeType() != Node.COMMENT_NODE && !nodes1.item(j).getNodeName().contains("#text")) {
                    paths.add("/" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName());
                    if (nodes1.item(j).hasAttributes()) {
                        for (int q = 0; q < nodes1.item(j).getAttributes().getLength(); q++) {
                            paths.add("/" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName() +
                                    "/@" + nodes1.item(j).getAttributes().item(q).getNodeName());
                        }
                    }
                }
                NodeList nodes2 = node2.getChildNodes();
                for (int k = 0; k < nodes2.getLength(); k++) {
                    Node node3 = nodes2.item(k);
                    if (node3.getNodeType() != Node.COMMENT_NODE && !nodes2.item(k).getNodeName().contains("#text")) {
                        paths.add("/" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName() + "/" + nodes2.item(k).getNodeName());
                        if (nodes2.item(k).hasAttributes()) {
                            for (int q = 0; q < nodes2.item(k).getAttributes().getLength(); q++) {
                                paths.add("/" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName() +
                                        "/" + nodes2.item(k).getNodeName() +
                                        "/@" + nodes2.item(k).getAttributes().item(q).getNodeName());
                            }
                        }
                    }
                    NodeList nodes3 = node3.getChildNodes();
                    for (int m = 0; m < nodes3.getLength(); m++) {
                        Node node4 = nodes3.item(m);
                        if (node4.getNodeType() != Node.COMMENT_NODE && !nodes3.item(m).getNodeName().contains("#text")) {
                            paths.add("/" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName() + "/" +
                                    nodes2.item(k).getNodeName() + "/" + nodes3.item(m).getNodeName());
                            if (nodes3.item(m).hasAttributes()) {
                                for (int q = 0; q < nodes3.item(m).getAttributes().getLength(); q++) {
                                    paths.add("/" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName() +
                                            "/" + nodes2.item(k).getNodeName() + "/" + nodes3.item(m).getNodeName() +
                                            "/@" + nodes3.item(m).getAttributes().item(q).getNodeName());
                                }
                            }
                        }
                        NodeList nodes4 = node4.getChildNodes();
                        for (int n = 0; n < nodes4.getLength(); n++) {
                            Node node5 = nodes4.item(n);
                            if ( node5.getNodeType() != Node.COMMENT_NODE && !nodes4.item(n).getNodeName().contains("#text")) {
                                paths.add("/" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName() + "/" +
                                        nodes2.item(k).getNodeName() + "/" + nodes3.item(m).getNodeName() + "/" + nodes4.item(n).getNodeName());
                                if (nodes4.item(n).hasAttributes()) {
                                    for (int q = 0; q < nodes4.item(n).getAttributes().getLength(); q++) {
                                        paths.add("/" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName() +
                                                "/" + nodes2.item(k).getNodeName() + "/" + nodes3.item(m).getNodeName() +
                                                "/" + nodes4.item(n).getNodeName() +
                                                "/@" + nodes4.item(n).getAttributes().item(q).getNodeName());
                                    }
                                }
                            }
                            NodeList nodes5 = node5.getChildNodes();
                            for (int b = 0; b < nodes5.getLength(); b++) {
                                Node node6 = nodes5.item(b);
                                if ( node6.getNodeType() != Node.COMMENT_NODE && !nodes5.item(b).getNodeName().contains("#text")) {
                                    paths.add("/" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName() + "/" +
                                            nodes2.item(k).getNodeName() + "/" + nodes3.item(m).getNodeName() + "/" + nodes4.item(n).getNodeName() + "/" + nodes5.item(b).getNodeName());
                                    if (nodes5.item(b).hasAttributes()) {
                                        for (int q = 0; q < nodes5.item(b).getAttributes().getLength(); q++) {
                                            paths.add("/" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName() +
                                                    "/" + nodes2.item(k).getNodeName() + "/" + nodes3.item(m).getNodeName() +
                                                    "/" + nodes4.item(n).getNodeName() +
                                                    "/" + nodes5.item(b).getNodeName() +
                                                    "/@" + nodes5.item(b).getAttributes().item(q).getNodeName());
                                        }
                                    }
                                }
                                NodeList nodes6 = node6.getChildNodes();
                                for (int a = 0; a < nodes6.getLength(); a++) {
                                    Node node7 = nodes6.item(a);
                                    if ( node7.getNodeType() != Node.COMMENT_NODE && !nodes6.item(a).getNodeName().contains("#text")) {
                                        paths.add("/" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName() + "/" +
                                                nodes2.item(k).getNodeName() + "/" + nodes3.item(m).getNodeName() + "/" + nodes4.item(n).getNodeName() + "/" + nodes5.item(b).getNodeName() + "/" + nodes6.item(a).getNodeName());
                                        if (nodes6.item(a).hasAttributes()) {
                                            for (int q = 0; q < nodes6.item(a).getAttributes().getLength(); q++) {
                                                paths.add("/" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName() +
                                                        "/" + nodes2.item(k).getNodeName() + "/" + nodes3.item(m).getNodeName() +
                                                        "/" + nodes4.item(n).getNodeName() +
                                                        "/" + nodes5.item(b).getNodeName() +
                                                        "/" + nodes6.item(a).getNodeName() +
                                                        "/@" + nodes6.item(a).getAttributes().item(q).getNodeName());
                                            }
                                        }
                                    }
                                    NodeList nodes7 = node7.getChildNodes();
                                    for (int c = 0; c < nodes7.getLength(); c++) {
                                        Node node8 = nodes7.item(c);
                                        if (node8.getNodeType() != Node.COMMENT_NODE && !nodes7.item(c).getNodeName().contains("#text")) {
                                            paths.add("/" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName() + "/" +
                                                    nodes2.item(k).getNodeName() + "/" + nodes3.item(m).getNodeName() + "/" + nodes4.item(n).getNodeName() + "/" + nodes5.item(b).getNodeName() + "/" + nodes6.item(a).getNodeName() + "/" + nodes7.item(c).getNodeName());
                                            if (nodes7.item(c).hasAttributes()) {
                                                for (int q = 0; q < nodes7.item(c).getAttributes().getLength(); q++) {
                                                    paths.add("/" + nodes.item(i).getNodeName() + "/" + nodes1.item(j).getNodeName() +
                                                            "/" + nodes2.item(k).getNodeName() + "/" + nodes3.item(m).getNodeName() +
                                                            "/" + nodes4.item(n).getNodeName() +
                                                            "/" + nodes5.item(b).getNodeName() +
                                                            "/" + nodes6.item(a).getNodeName() +
                                                            "/" + nodes7.item(c).getNodeName() +
                                                            "/@" + nodes7.item(c).getAttributes().item(q).getNodeName());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return paths;
    }


    //TODO: REFACTOR THIS
    public List<String> changePathsPrefix(List<String> paths) throws IOException, ParserConfigurationException, SAXException {
        Document document = XsdParser.buildDocumentFromFile(rootXml);
        Node node = document.getDocumentElement();
        NamedNodeMap tags = node.getAttributes();
        LinkedHashMap<String, String> prefixMap = new LinkedHashMap<>();
        for (int i = 0; i < tags.getLength(); i++) {
            if (tags.item(i).toString().contains("xmlns")) {
                List<String> splitedTags = List.of(tags.item(i).toString().split("="));
                String fileName = List.of(tags.item(i).toString().split("\"")).get(1).toString().
                        replace(":", "_").replace("cus_", "") + ".xsd";
                String oldPrefix = splitedTags.get(0).replace("xmlns:", "");
                prefixMap.put(oldPrefix, fileName);
            }
        }
        LinkedHashMap<String, String> newPrefixMap = new LinkedHashMap<>();
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
                        if(!key.equals("cus")){
                            String replace = split.replace(key, newPrefixMap.get(key));
                            if (!replace.contains(":1")) {
                                result = result + replace;
                            }
                        } else {
                            if(split.indexOf("cus")==0){
                                String replace = split.replaceFirst(key, newPrefixMap.get(key));
                                if (!replace.contains(":1")) {
                                    result = result + replace;
                                }
                            }
                        }
                    }
                }
                if (split.contains("@")) {
                    result = result + "/" + split;
                }
            }
            String rootNodeName = List.of(node.getNodeName().split(":")).get(1);
            String rootPrefix = XsdService.getPathPrefixFromFile(new File(rootXml.getName().replace(".xml", "")));
            changesPrefixPaths.add(rootPrefix + rootNodeName + result.replace("::", ":"));
        }
        return changesPrefixPaths;
    }

    public LinkedHashMap<String, String> getElementsPathAndNameMap(List<String> elementsPaths) throws IOException, ParserConfigurationException, SAXException {
        LinkedHashMap<String, String> pathAndNameMap = new LinkedHashMap<>();
        for (String elementPath : elementsPaths) {
            List<String> splitedElementPath = List.of(elementPath.split("/"));
            if (!(splitedElementPath.get(splitedElementPath.size() - 1).contains("@"))) {
                pathAndNameMap.put(elementPath, List.of(splitedElementPath.get(splitedElementPath.size() - 1).split(":")).get(0));
            } else {
                pathAndNameMap.put(elementPath, splitedElementPath.get(splitedElementPath.size() - 1).replace("@", ""));
            }
        }
        pathAndNameMap.put(getRootElementPath(), getRootElementName());
        return pathAndNameMap;
    }

    public String getRootElementName() throws IOException, ParserConfigurationException, SAXException {
        Document document = XsdParser.buildDocumentFromFile(rootXml);
        Element node = document.getDocumentElement();
        return List.of(node.getNodeName().split(":")).get(1);
    }

    public String getRootElementPath() throws IOException, ParserConfigurationException, SAXException {
        return XsdService.getPathPrefixFromFile(new File(rootXml.getName().replace(".xml", ""))) + getRootElementName();
    }

    public LinkedHashMap<String, String> getPathAndDocumentationMap(LinkedHashMap<String, String> elementsPathAndNameMap) throws IOException, ParserConfigurationException, SAXException {
        LinkedHashMap<String, String> pathAndDocumentationMap = new LinkedHashMap<>();
        List<String> fileNames = getFileNames();
        for (Map.Entry<String, String> entry : elementsPathAndNameMap.entrySet()) {
            String entryName = entry.getValue();
            for (String fileName : fileNames) {
                Document document = XsdParser.buildDocumentFromFile(new File(fileName));
                NodeList documentations = document.getElementsByTagName("xs:documentation");
                for (int i = 0; i < documentations.getLength(); i++) {
                    Element element = (Element) documentations.item(i);
                    Element parentElement = (Element) element.getParentNode().getParentNode();
                    String attributeName = parentElement.getAttribute("name");
                    if (!attributeName.isEmpty()) {
                        if (entryName.equals(attributeName)) {
                            pathAndDocumentationMap.put(entry.getKey(), element.getTextContent());
                        }
                    }
                }
            }
        }
        return pathAndDocumentationMap;
    }

    public List<String> getFileNames() throws IOException, ParserConfigurationException, SAXException {
        List<String> fileNames = new ArrayList<>();
        Document document = XsdParser.buildDocumentFromFile(rootXml);
        Node node = document.getDocumentElement();
        NamedNodeMap tags = node.getAttributes();
        for (int i = 0; i < tags.getLength(); i++) {
            if (tags.item(i).toString().contains("xmlns")) {
                String fileName = List.of(tags.item(i).toString().split("\"")).get(1).toString().
                        replace(":", "_").replace("cus_", "") + ".xsd";
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

    public LinkedHashMap<String, String> getAllPatternsMap() throws IOException, ParserConfigurationException, SAXException {
        LinkedHashMap<String, String> patternsMap = new LinkedHashMap<>();
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


    public LinkedHashMap<String, String> getUseMap() throws IOException, ParserConfigurationException, SAXException {
        LinkedHashMap<String, String> useMap = new LinkedHashMap<>();
        List<String> fileNames = getFileNames();
        for (String fileName : fileNames) {
            Document document = XsdParser.buildDocumentFromFile(new File(fileName));
            NodeList patterns = document.getElementsByTagName("xs:attribute");
            for (int i = 0; i < patterns.getLength(); i++) {
                Element element = (Element) patterns.item(i);
                if (element.hasAttribute("use")){
                    String useAtr = element.getAttribute("use");
                    if (useAtr.length()>0){
                        System.out.println("USE_MAP");
                        String elementName = element.getAttribute("name");
                        String finalName = List.of(elementName.split(":")).get(0);
                        useMap.put(finalName, useAtr);
                    }
                }
            }
        }
        System.out.println("SIZEMAP_USE "+ useMap.size());

        for(Map.Entry<String, String> entry:useMap.entrySet()){
            System.out.println(entry.getKey()+" " +entry.getValue());
        }
        return useMap;
    }

    public LinkedHashMap<String, String> getDefaultMap() throws IOException, ParserConfigurationException, SAXException {
        LinkedHashMap<String, String> defaultMap = new LinkedHashMap<>();
        List<String> fileNames = getFileNames();
        for (String fileName : fileNames) {
            Document document = XsdParser.buildDocumentFromFile(new File(fileName));
            NodeList patterns = document.getElementsByTagName("xs:attribute");
            for (int i = 0; i < patterns.getLength(); i++) {
                Element element = (Element) patterns.item(i);
                if (element.hasAttribute("default")){
                    String useAtr = element.getAttribute("default");
                    if (useAtr.length()>0){
                        System.out.println("DEFAULT_MAP");
                        String elementName = element.getAttribute("name");
                        String finalName = List.of(elementName.split(":")).get(0);
                        defaultMap.put(finalName, useAtr);
                    }
                }

            }
        }
        System.out.println("SIZEMAP_default "+ defaultMap.size());

        for(Map.Entry<String, String> entry:defaultMap.entrySet()){
            System.out.println(entry.getKey()+" " +entry.getValue());
        }
        return defaultMap;
    }



    public String getPatternForElementByName(String elementName) throws IOException, ParserConfigurationException, SAXException {
        LinkedHashMap<String, String> patterns = getAllPatternsMap();
        String pattern = null;
        pattern = patterns.get(elementName);
        return pattern;
    }
    //TODO ХУЕТА ПЕРЕДЕЛАТЬ
    public String getConditionByMultiplicity(LinkedHashMap<String,String> minMultiplicityMap, LinkedHashMap<String,String> maxMultiplicityMap, LinkedHashMap<String,String> useMap, LinkedHashMap<String,String> defaultMap, String elementName) throws IOException, ParserConfigurationException, SAXException {
        String condition = null;

        String minMultiplicity = minMultiplicityMap.get(elementName);
        String maxMultiplicity = maxMultiplicityMap.get(elementName);
        String useMultiplicity = useMap.get(elementName);
        String defaultMultiplicity = defaultMap.get(elementName);
        if (maxMultiplicity == null && minMultiplicity == null && defaultMultiplicity == null && useMultiplicity == null) {
            return "1";
        } else if (maxMultiplicity == null && minMultiplicity == null) {
            return "1";
        } else if (minMultiplicity == null && maxMultiplicity.equals("unbounded")) {
            return "1..*";
        } else if (minMultiplicity != null && minMultiplicity.equals("0") && maxMultiplicity == null) {
            return "0..1";
        } else if (minMultiplicity != null && minMultiplicity.equals("0") && maxMultiplicity.equals("1")) {
            return "0..1";
        } else if (minMultiplicity != null && minMultiplicity.equals("0") && maxMultiplicity.equals("unbounded") || maxMultiplicity.matches("[2-9]{1,}")) {
            if (maxMultiplicity.equals("unbounded")) {
                return "0..*";
            } else {
                return "0.." + maxMultiplicity;
            }
        } else if (minMultiplicity != null && minMultiplicity.matches("[1-9]*") && maxMultiplicity.equals("unbounded") || maxMultiplicity.matches("[2-9]{1,}")) {
            if (maxMultiplicity.equals("unbounded")) {
                return minMultiplicity + "..*";
            } else {
                return minMultiplicity + ".." + maxMultiplicity;
            }
        } else if (useMultiplicity != null && useMultiplicity.equals("optional") && defaultMultiplicity == null) {
            return "0..*" ;
        } else if (useMultiplicity != null && useMultiplicity.equals("required") && defaultMultiplicity == null) {
            return "1..*";
        } else if (useMultiplicity != null && useMultiplicity.equals("required") && defaultMultiplicity.matches("[1-9]{1,}")) {
            return defaultMultiplicity + "..*";
        }
        return condition;
    }

    public LinkedHashMap<String, String> getMinAllMultMap() throws IOException, ParserConfigurationException, SAXException {
        LinkedHashMap<String, String> pathAndMinMap = new LinkedHashMap<>();
        List<String> fileNames = getFileNames();
        for (String fileName : fileNames) {
            Document document = XsdParser.buildDocumentFromFile(new File(fileName));
            NodeList patterns = document.getElementsByTagName("xs:element");
            for (int i = 0; i < patterns.getLength(); i++) {
                Element element = (Element) patterns.item(i);
                if (element.hasAttribute("minOccurs")){
                    String minOccurs = element.getAttribute("minOccurs");
                    if (minOccurs.length()>0){
                        System.out.println("HERE");
                        String elementName=element.getAttribute("ref");
                        String finalName= List.of(elementName.split(":")).get(0);
                        pathAndMinMap.put(finalName,minOccurs);
                    }
                }
            }
        }
        System.out.println("SIZEMAP "+ pathAndMinMap.size());

        for(Map.Entry<String, String> entry:pathAndMinMap.entrySet()){
            System.out.println(entry.getKey()+" " +entry.getValue());
        }
        return pathAndMinMap;
    }

    public LinkedHashMap<String,String> getMaxAllMultMap() throws IOException, ParserConfigurationException, SAXException {
        LinkedHashMap<String, String> pathAndMinMap = new LinkedHashMap<>();
        List<String> fileNames = getFileNames();
        for (String fileName : fileNames) {
            Document document = XsdParser.buildDocumentFromFile(new File(fileName));
            NodeList patterns = document.getElementsByTagName("xs:element");
            for (int i = 0; i < patterns.getLength(); i++) {
                Element element = (Element) patterns.item(i);
                if (element.hasAttribute("maxOccurs")){
                    String maxOccurs = element.getAttribute("maxOccurs");
                    if (maxOccurs.length()>0){
                        System.out.println(maxOccurs);
                        String elementName=element.getAttribute("ref");
                        String finalName= List.of(elementName.split(":")).get(0);
                        System.out.println(element.getAttribute("ref"));
                        pathAndMinMap.put(finalName,maxOccurs);
                    }
                }
            }
        }
        return pathAndMinMap;
    }

//    public String getMinForElementByName(String elementName) throws IOException, ParserConfigurationException, SAXException {
//        Map<String, String> min = getMinAllMultMap();
//        String result = null;
//        result = min.get(elementName);
//        return result;
//    }
//
//    public String getRootElementDocumentation() throws IOException, ParserConfigurationException, SAXException {
//        Document document = buildDocumentFromFile(new File(rootXml.getName().replace(".xml", "")));
//        NodeList documentations = document.getElementsByTagName("xs:documentation");
//        Element element = (Element) documentations.item(0);
//        return element.getTextContent();
//    }
}









