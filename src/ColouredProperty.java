import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The Property class extends BoardSpace and represents
 * a space that is specifically a property
 *
 * @author Evan Smedley
 * @version 1.0
 * @since 2021-10-22
 */
public class ColouredProperty extends Property {
    /**
     * Whether the property is available or not
     */
    private boolean isAvailable;

    /**
     * The price of the property
     */
    private final int price;

    /**
     * The color group of the property
     */
    private final String color;

    private Player owner;

    private ImageIcon square;

    /**
     * The Property constructor
     *
     * @param name  String, The name of the property
     * @param price int, The price of the property
     * @param color String, The color of the property
     */
    public ColouredProperty(String name, int price, String type, String color, String path, int position) {
        super(name, price, type, color, path, position);
        this.isAvailable = true;
        this.price = price;
        this.color = color;
        this.square = new ImageIcon(path);
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
        return isAvailable == property.isAvailable && price == property.price && Objects.equals(color, property.color) && Objects.equals(owner, property.owner);
    }
}