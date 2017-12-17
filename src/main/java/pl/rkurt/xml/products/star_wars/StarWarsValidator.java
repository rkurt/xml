package pl.rkurt.xml.products.star_wars;

import pl.rkurt.xml.validator.AbstractXMLValidator;

import java.io.InputStream;

public class StarWarsValidator extends AbstractXMLValidator {

    public StarWarsValidator(InputStream file) {
        super(file);
    }

    @Override
    public String getSchemaFileName() {
        return "StarWars.xsd";
    }
}