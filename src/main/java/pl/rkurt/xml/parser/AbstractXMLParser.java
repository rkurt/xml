package pl.rkurt.xml.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pl.rkurt.xml.products.AbstractProduct;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public abstract class AbstractXMLParser<T extends AbstractProduct> implements Parser<T> {

    protected static final String EMPTY_TEXT = "";
    protected static final int EMPTY_NUMBER = 0;
    private static final String PRODUCTS = "Product";
    private final InputStream file;
    private List<T> data = new ArrayList<>();
    private Document dom;

    public AbstractXMLParser(InputStream file) {
        this.file = file;
    }

    @Override
    public List<T> parse() throws ParseException {
        readFile();
        parseData();

        return data;
    }

    private void readFile() throws ParseException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.parse(file);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            throw new ParseException();
        }
    }

    private void parseData() {
        Element element = dom.getDocumentElement();
        NodeList nodeList = element.getElementsByTagName(PRODUCTS);
        if (checkNode.test(nodeList)) {
            IntStream.range(0, nodeList.getLength()).mapToObj(nodeList::item).forEach(n -> data.add(parseElement((Element) n)));
        }
    }
}
