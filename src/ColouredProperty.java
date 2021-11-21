import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The Property class extends BoardSpace and represents
 * a space that is specifically a property
 *
 * @author Shuvaethy Neill
 * @version 1.0
 * @since 2021-11-17
 */
public class ColouredProperty extends Property {

    /**
     * The Property constructor
     *
     * @param name  String, The name of the property
     * @param price int, The price of the property
     * @param color String, The color of the property
     */
    public ColouredProperty(String name, int price, String type, String color, String path, int position) {
        super(name, price, type, color, path, position);
    }

    /**
     * Determines if a property is equivalent to another property
     *
     * @param o Object, the object to be compared with the object this method is called on
     * @return boolean, if the objects are equivalent
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColouredProperty property = (ColouredProperty) o;
        return this.isAvailable == property.isAvailable && this.price == property.price && Objects.equals(this.color, property.color) && Objects.equals(this.owner, property.owner);
    }
}