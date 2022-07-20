package by.btslogistics.beltamozhservisproject.parser.xsd;

import com.stratumsoft.xmlgen.DefaultValues;
import com.stratumsoft.xmlgen.SchemaTypeXmlGenerator;
import com.stratumsoft.xmlgen.XmlGenOptions;
import org.apache.ws.commons.schema.XmlSchema;
import org.apache.ws.commons.schema.XmlSchemaCollection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class XsdParser {

    public static Document buildDocumentFromFile(File file) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        return docBuilder.parse(file);
    }

}
