package pl.rkurt.xml.validator;

import org.springframework.core.io.ClassPathResource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractXMLValidator implements Validator {

    private final InputStream file;

    public AbstractXMLValidator(InputStream file) {
        this.file = file;
    }

    @Override
    public void validate() throws ValidationException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(getSchemaFile());
            javax.xml.validation.Validator validator = schema.newValidator();
            validator.validate(new StreamSource(file));
        } catch (IOException | SAXException e) {
            e.printStackTrace();
            if (e instanceof SAXParseException) {
                throw new ValidationException(((SAXParseException) e).getLineNumber(), ((SAXParseException) e).getColumnNumber());
            }
            throw new ValidationException();
        }
    }

    private File getSchemaFile() throws IOException {
        return new ClassPathResource(getSchemaFileName()).getFile();
    }
}
