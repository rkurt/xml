package pl.rkurt.xml.products;

import lombok.Getter;
import pl.rkurt.xml.parser.ParseException;
import pl.rkurt.xml.products.marvel.Marvel;
import pl.rkurt.xml.products.marvel.MarvelParser;
import pl.rkurt.xml.products.marvel.MarvelValidator;
import pl.rkurt.xml.products.star_wars.StarWars;
import pl.rkurt.xml.products.star_wars.StarWarsParser;
import pl.rkurt.xml.products.star_wars.StarWarsValidator;
import pl.rkurt.xml.validator.ValidationException;

import java.io.InputStream;
import java.util.List;

public enum ProductType {
    STAR_WARS("Star Wars") {
        @Override
        public List<StarWars> parseProducts(InputStream file) throws ParseException {
            return new StarWarsParser(file).parse();
        }

        @Override
        public void validateProducts(InputStream file) throws ValidationException {
            new StarWarsValidator(file).validate();
        }

        @Override
        public List<String> getFieldsNames() {
            return StarWars.getPrintableFieldsNames();
        }
    },
    MARVEL("Marvel") {
        @Override
        public List<Marvel> parseProducts(InputStream file) throws ParseException {
            return new MarvelParser(file).parse();
        }

        @Override
        public void validateProducts(InputStream file) throws ValidationException {
            new MarvelValidator(file).validate();
        }

        @Override
        public List<String> getFieldsNames() {
            return Marvel.getPrintableFieldsNames();
        }
    };

    @Getter
    private String name;

    ProductType(String name) {
        this.name = name;
    }

    /**
     * Parse products from file.
     *
     * @param file Defined XML file
     * @return List of products {@link AbstractProduct}
     * @throws ParseException Exception when file parsing error
     */
    public abstract List<? extends AbstractProduct> parseProducts(InputStream file) throws ParseException;

    /**
     * Validate products from file.
     *
     * @param file Defined file
     * @throws ValidationException Exception if invalid with XSD
     */
    public abstract void validateProducts(InputStream file) throws ValidationException;

    /**
     * Get fields names for product.
     *
     * @return List od field names
     */
    public abstract List<String> getFieldsNames();
}
