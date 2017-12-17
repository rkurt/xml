package pl.rkurt.xml.products.star_wars;

import lombok.Builder;
import lombok.Getter;
import pl.rkurt.xml.products.AbstractProduct;

import java.util.Arrays;
import java.util.List;

@Getter
@Builder
public class StarWars extends AbstractProduct {

    public static final String ID = "Id";
    public static final String NAME = "Name";
    public static final String SIDE = "Side";

    private int id;
    private String name;
    private String side;

    @Override
    public List<String> getPrintableValues() {
        return Arrays.asList(String.valueOf(getId()), getName(), getSide());
    }

    /**
     * Get list of field names to print in table.
     *
     * @return List of field names
     */
    public static List<String> getPrintableFieldsNames() {
        return Arrays.asList(ID, NAME, SIDE);
    }
}