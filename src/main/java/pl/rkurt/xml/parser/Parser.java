package pl.rkurt.xml.parser;

import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import pl.rkurt.xml.products.AbstractProduct;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Parser<T extends AbstractProduct> {

    String SELECT_FILE = "Please select a XML file";

    Predicate<NodeList> checkNode = n -> n != null && n.getLength() > 0;

    Predicate<MultipartFile> checkFile = f -> f.isEmpty() || !f.getContentType().equals("text/xml");

    /**
     * Parse defined XML file.
     *
     * @return List of returning objects
     * @throws ParseException Exception when file parsing error
     */
    List<T> parse() throws ParseException;

    /**
     * Parse single element.
     *
     * @param element Defined element {@link Element}
     * @return Returning object
     */
    T parseElement(Element element);

    /**
     * Get text value from element by tag name.
     *
     * @param element Defined element
     * @param tagName Defined tag name
     * @return Text value
     */
    static Optional<String> getStringValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        return checkNode.test(nodeList) ? Optional.of((nodeList.item(0)).getFirstChild().getNodeValue()) : Optional.empty();
    }

    /**
     * Get number value from element by tag name.
     *
     * @param element Defined element
     * @param tagName Defined tag name
     * @return Number value
     */
    static Optional<Integer> getIntegerValue(Element element, String tagName) {
        return getStringValue(element, tagName).map(Integer::parseInt);
    }
}
