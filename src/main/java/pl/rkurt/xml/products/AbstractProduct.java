package pl.rkurt.xml.products;

import java.util.List;

public abstract class AbstractProduct {

    /**
     * Get list of field values to print in table.
     *
     * @return List of field values
     */
    public abstract List<String> getPrintableValues();
}
