import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The Utility class extends Property and represents
 * a space that is specifically a utility property
 *
 * @author Shuvaethy Neill
 * @version 1.0
 * @since 2021-11-17
 */
public class Utility extends Property {

    /**
     *
     */
    public Utility() {
        this("", -1, "", "", "", -1);
    }

    /**
     * The Utility constructor
     *
     * @param name  String, The name of the property
     * @param price int, The price of the property
     * @param type String, type of property
     * @param colour String, The color of the property
     */
    public Utility(String name, int price, String type, String colour, String path, int position) {
        super(name, price, type, colour, path, position);

        ImageIcon utilityIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/utility.png")));
        centerPanel.add(new JLabel(new ImageIcon(utilityIcon.getImage().getScaledInstance(40, 40, Image.SCALE_FAST))), BorderLayout.CENTER);
    }

    /**
     * Get the price of rent for the utility
     *
     * @return int, the price of utility
     */
    public int getRent() {
        int utilityCount = 0;
        int rent;
        for(int i = 0; i < this.owner.getProperties().size(); i++){
            if(this.owner.getProperties().get(i) instanceof Utility){
                utilityCount++;
            }
        }
        if(utilityCount == 1){
            rent = 4;
        }
        else{
            rent = 10;
        }
        return rent;
    }

    /**
     * Displays utility landed on along with if it's available for purchase or they pay rent.
     */
    @Override
    public String displayInfo() {
        String text = "\nYou are now located at: " + getName();
        if (isAvailable){
            text += "\nThis utility costs $" + getPrice();
        }
        else{
            text += "\nThe rent for this utility is $" + getRent() + " multiplied by your dice roll.";
        }
        return text;
    }

    /**
     * Determines if a utility is equivalent to another utility
     *
     * @param o Object, the object to be compared with the object this method is called on
     * @return boolean, if the objects are equivalent
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utility utility = (Utility) o;
        return isAvailable == utility.isAvailable && price == utility.price && Objects.equals(owner, utility.owner);
    }

    /**
     * Returns a String representation of the Utility object
     *
     * @return String, the utility object as a string value
     */
    @Override
    public String toString() {
        return "Name: " + this.getName() + ", Price: " + this.getPrice();
    }
}
