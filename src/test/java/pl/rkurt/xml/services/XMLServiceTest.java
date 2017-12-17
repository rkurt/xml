package pl.rkurt.xml.services;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import pl.rkurt.xml.parser.ParseException;
import pl.rkurt.xml.products.ProductType;
import pl.rkurt.xml.products.marvel.Marvel;
import pl.rkurt.xml.products.star_wars.StarWars;
import pl.rkurt.xml.validator.ValidationException;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class XMLServiceTest {

    private XMLService service;

    @Before
    public void setUp() {
        service = new XMLService();
    }

    @Test
    public void testParseStarWarsProducts() throws IOException, ParseException {
        //noinspection unchecked
        List<StarWars> result = (List<StarWars>) service.parseProducts(new ClassPathResource("StarWars.xml").getInputStream(), ProductType.STAR_WARS);

        assertThat(result.size(), is(5));
        //noinspection unchecked
        assertThat(result, contains(
                matchStarWars(1, "Tie Fighter", "Empire"),
                matchStarWars(2, "X-Wing", "Rebels"),
                matchStarWars(3, "AT-ST", "Empire"),
                matchStarWars(4, "Han Solo", "Rebels"),
                matchStarWars(5, "Lord Vader", "Empire")
        ));
    }

    @Test
    public void testParseMarvelProducts() throws IOException, ParseException {
        //noinspection unchecked
        List<Marvel> result = (List<Marvel>) service.parseProducts(new ClassPathResource("Marvel.xml").getInputStream(), ProductType.MARVEL);

        assertThat(result.size(), is(4));
        //noinspection unchecked
        assertThat(result, contains(
                matchMarvel(1, "Spider Man", "Spider's web"),
                matchMarvel(2, "Captain America", "Shield"),
                matchMarvel(3, "Hulk", "Strength"),
                matchMarvel(4, "Iron Man", "Armor")
        ));
    }

    @Test
    public void validateStarWarsProducts() throws IOException, ValidationException {
        service.validateProducts(new ClassPathResource("StarWars.xml").getInputStream(), ProductType.STAR_WARS);
    }

    @Test(expected = ValidationException.class)
    public void validateStarWarsProductsWhenInvalid() throws IOException, ValidationException {
        service.validateProducts(new ClassPathResource("StarWars.xml").getInputStream(), ProductType.MARVEL);
    }

    @Test
    public void validateMarvelProducts() throws IOException, ValidationException {
        service.validateProducts(new ClassPathResource("Marvel.xml").getInputStream(), ProductType.MARVEL);
    }

    @Test(expected = ValidationException.class)
    public void validateMarvelProductsWhenInvalid() throws IOException, ValidationException {
        service.validateProducts(new ClassPathResource("Marvel.xml").getInputStream(), ProductType.STAR_WARS);
    }

    private Matcher<StarWars> matchStarWars(final int id, final String name, final String side) {
        return new BaseMatcher<StarWars>() {
            @Override
            public boolean matches(final Object item) {
                final StarWars starWars = (StarWars) item;
                return id == starWars.getId() && name.equals(starWars.getName()) && side.equals(starWars.getSide());
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("getId should return ").appendValue(id);
                description.appendText(", getName should return ").appendValue(name);
                description.appendText(", getSide should return ").appendValue(side);
            }
        };
    }

    private Matcher<Marvel> matchMarvel(final int id, final String name, final String feature) {
        return new BaseMatcher<Marvel>() {
            @Override
            public boolean matches(final Object item) {
                final Marvel marvel = (Marvel) item;
                return id == marvel.getId() && name.equals(marvel.getName()) && feature.equals(marvel.getFeature());
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("getId should return ").appendValue(id);
                description.appendText(", getName should return ").appendValue(name);
                description.appendText(", getFeature should return ").appendValue(feature);
            }
        };
    }
}