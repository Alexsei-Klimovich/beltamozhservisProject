package by.btslogistics.beltamozhservisproject.service;

import org.apache.ws.commons.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class XsdService {
    private XmlSchemaCollection xmlSchemaCollection;
    private static Map<QName, List<XmlSchemaElement>> xsdElements = new HashMap<>();
    private List<XmlSchemaElement> schemaElements = new ArrayList<>();

    @Autowired
    StructureDocumentService structureDocumentService;

    @Autowired
    GrafaService grafaService;

    public void xsdParse() throws URISyntaxException, FileNotFoundException {
        String xsdPath = Paths.get
                (XsdService.class.getClassLoader().
                        getResource("C:\\Users\\0_shtykh_ya\\IdeaProjects\\beltamozhservisProject\\EEC_M_CA_SimpleDataObjects_vbts2.xsd")
                        .toURI()).toFile().getAbsolutePath();
        String filePath = Path.of(xsdPath).toString();

        InputStream inputStream = new FileInputStream(filePath);
        xmlSchemaCollection = new XmlSchemaCollection();

        XmlSchema schema = xmlSchemaCollection.read(new StreamSource(inputStream));
        Map.Entry<QName, XmlSchemaElement> entry = schema.getElements().entrySet().iterator().next();
        QName rootElement = entry.getKey();

        XmlSchemaElement childElement = xmlSchemaCollection.getElementByQName(rootElement);

        getChildElementNames(childElement);
        String element = "" + xsdElements.entrySet().stream().map(e -> e.getKey() + " -- " + String.join(", ", e.getValue().stream().map(v -> v
                .getQName().toString()).collect(Collectors.toList()))).collect(Collectors.toList());
        System.out.println(element);
    }

    private void getChildElementNames(XmlSchemaElement element) {
        XmlSchemaType elementType = element != null ? element.getSchemaType() : null;

        if (elementType instanceof XmlSchemaComplexType) {
            XmlSchemaParticle allParticles = ((XmlSchemaComplexType) elementType).getParticle();

            if (allParticles instanceof XmlSchemaAny) {
                System.out.println("Any Schema Type");

            } else if (allParticles instanceof XmlSchemaElement) {
                System.out.println("Element Schema Type");

            } else if (allParticles instanceof XmlSchemaSequence) {
                final XmlSchemaSequence xmlSchemaSequence = (XmlSchemaSequence) allParticles;
                final List<XmlSchemaSequenceMember> items = xmlSchemaSequence.getItems();
                items.forEach((item) -> {
                    XmlSchemaElement itemElements = (XmlSchemaElement) item;
                    schemaElements.add(itemElements);

                    addChild(element.getQName(), itemElements);
                    getChildElementNames(itemElements);
                    schemaElements = new ArrayList<XmlSchemaElement>();
                });

            } else if (allParticles instanceof XmlSchemaGroupRef) {

            }
        }

    }

    public static void addChild(QName qName, XmlSchemaElement child) {
        List<XmlSchemaElement> values = xsdElements.get(qName);
        if (values == null) {
            values = new ArrayList<XmlSchemaElement>();
        }
        values.add(child);
        xsdElements.put(qName, values);
    }
}