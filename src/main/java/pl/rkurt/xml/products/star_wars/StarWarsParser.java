package pl.rkurt.xml.products.star_wars;

import org.w3c.dom.Element;
import pl.rkurt.xml.parser.AbstractXMLParser;
import pl.rkurt.xml.parser.Parser;

import java.io.InputStream;

import static pl.rkurt.xml.products.star_wars.StarWars.*;

public class StarWarsParser extends AbstractXMLParser<StarWars> {

    public StarWarsParser(InputStream file) {
        super(file);
    }

    @Override
    public StarWars parseElement(Element element) {
        return StarWars.builder()
                .id(Parser.getIntegerValue(element, ID).orElse(EMPTY_NUMBER))
                .name(Parser.getStringValue(element, NAME).orElse(EMPTY_TEXT))
                .side(Parser.getStringValue(element, SIDE).orElse(EMPTY_TEXT))
                .build();
    }
}
