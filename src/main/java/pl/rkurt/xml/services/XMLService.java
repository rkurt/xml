package pl.rkurt.xml.services;

import org.springframework.stereotype.Service;
import pl.rkurt.xml.parser.ParseException;
import pl.rkurt.xml.products.AbstractProduct;
import pl.rkurt.xml.products.ProductType;
import pl.rkurt.xml.validator.ValidationException;

import java.io.InputStream;
import java.util.List;

@Service
public class XMLService {

    /**
     * Parse products from file by product type.
     *
     * @param file        Defined XML file
     * @param productType Defined product type
     * @return List of products
     * @throws ParseException Exception when file parsing error
     */
    public List<? extends AbstractProduct> parseProducts(InputStream file, ProductType productType) throws ParseException {
        return productType.parseProducts(file);
    }

    /**
     * Validate products from file by product type.
     *
     * @param file        Defined XML file
     * @param productType Defined product type
     * @throws ValidationException Exception if invalid
     */
    public void validateProducts(InputStream file, ProductType productType) throws ValidationException {
        productType.validateProducts(file);
    }
}
