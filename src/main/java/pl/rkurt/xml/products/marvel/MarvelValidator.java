package pl.rkurt.xml.products.marvel;

import pl.rkurt.xml.validator.AbstractXMLValidator;

import java.io.InputStream;

public class MarvelValidator extends AbstractXMLValidator {

    public MarvelValidator(InputStream file) {
        super(file);
    }

    @Override
    public String getSchemaFileName() {
        return "Marvel.xsd";
    }
}
