package pl.rkurt.xml.validator;

public interface Validator {

    /**
     * Validate defined XML file.
     *
     * @throws ValidationException Exception if invalid with XSD
     */
    void validate() throws ValidationException;

    /**
     * Get XSD file name.
     *
     * @return XSD file name
     */
    String getSchemaFileName();
}
