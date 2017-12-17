package pl.rkurt.xml.validator;

public class ValidationException extends Exception {
    public ValidationException() {
        super("Products validating error");
    }

    ValidationException(int lineNr, int columnNr) {
        super(String.format("Products validating error on line %s and column %s", lineNr, columnNr));
    }
}
