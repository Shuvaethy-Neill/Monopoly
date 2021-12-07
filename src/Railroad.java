import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The Railroad class extends Property and represents
 * a space that is specifically a railroad property
 *
 * @author Shuvaethy Neill
 * @version 1.0
 * @since 2021-11-17
 */
public class Railroad extends Property {

    /**
     *
     */
    public Railroad() {
        this("", -1, "", "", "", -1);
    }

    /**
     * The ColouredProperty constructor
     *
     * @param name  String, The name of the property
     * @param price int, The price of the property
     * @param type String, type of property
     * @param colour String, The color of the property
     */
    public Railroad(String name, int price, String type, String colour, String path, int position) {
        super(name, price, type, colour,  path, position);

        ImageIcon railroadIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/railroad.png")));
        centerPanel.add(new JLabel(new ImageIcon(railroadIcon.getImage().getScaledInstance(25, 25, Image.SCALE_FAST))), BorderLayout.CENTER);
    }

    /**
     * Get the price of rent for the railroad
     *
     * @return int, the price of railroad
     */
    @Override
    public int getRent() {
        int railroadCount = 0;
        int rent;
        for(int i = 0; i < this.owner.getProperties().size(); i++){
            if(this.owner.getProperties().get(i) instanceof Railroad){
                railroadCount++;
            }
        }
        if(railroadCount == 1){
            rent = 25;
        }
        else if(railroadCount == 2){
            rent = 50;
        }
        else if(railroadCount == 3){
            rent = 100;
        }
        else{
            rent = 200;
        }
        return rent;
    }

    /**
     * Displays railroad landed on along with if it's available for purchase or they pay rent.
     */
    @Override
    public String displayInfo() {
        String text = "\nYou are now located at: " + getName();
        if (this.isAvailable()){
            text += "\nThis railroad costs $" + getPrice();
        }
        else{
            text += "\nThe rent for this railroad is $" + getRent() + ".";
        }
        return text;
    }

    /**
     * Determines if a railroad is equivalent to another railroad
     *
     * @param o Object, the object to be compared with the object this method is called on
     * @return boolean, if the objects are equivalent
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Railroad railroad = (Railroad) o;
        return this.isAvailable == railroad.isAvailable && this.price == railroad.price && Objects.equals(this.owner, railroad.owner);
    }

    /**
     * Returns a String representation of the Railroad object
     *
     * @return String, the railroad object as a string value
     */
    @Override
    public String toString() {
        return "Name: " + this.getName() + ", Price: " + this.getPrice();
    }
}
