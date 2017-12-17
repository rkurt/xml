package pl.rkurt.xml.products.marvel;

import org.w3c.dom.Element;
import pl.rkurt.xml.parser.AbstractXMLParser;
import pl.rkurt.xml.parser.Parser;

import java.io.InputStream;

import static pl.rkurt.xml.products.marvel.Marvel.*;

public class MarvelParser extends AbstractXMLParser<Marvel> {

    public MarvelParser(InputStream file) {
        super(file);
    }

    @Override
    public Marvel parseElement(Element element) {
        return Marvel.builder()
                .id(Parser.getIntegerValue(element, ID).orElse(EMPTY_NUMBER))
                .name(Parser.getStringValue(element, NAME).orElse(EMPTY_TEXT))
                .feature(Parser.getStringValue(element, FEATURE).orElse(EMPTY_TEXT))
                .build();
    }
}
