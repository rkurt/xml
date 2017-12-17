package pl.rkurt.xml.products.marvel;

import lombok.Builder;
import lombok.Getter;
import pl.rkurt.xml.products.AbstractProduct;

import java.util.Arrays;
import java.util.List;

@Getter
@Builder
public class Marvel extends AbstractProduct {

    public static final String ID = "Id";
    public static final String NAME = "Name";
    public static final String FEATURE = "Feature";

    private int id;
    private String name;
    private String feature;

    @Override
    public List<String> getPrintableValues() {
        return Arrays.asList(String.valueOf(getId()), getName(), getFeature());
    }

    /**
     * Get list of fields names to print in table.
     *
     * @return List of fields names
     */
    public static List<String> getPrintableFieldsNames() {
        return Arrays.asList(ID, NAME, FEATURE);
    }
}