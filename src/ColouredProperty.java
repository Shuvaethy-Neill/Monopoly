import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The ColouredProperty class extends Property and represents
 * a space that is specifically a coloured type of property
 *
 * @author Shuvaethy Neill, Harsimran Kanwar
 * @version 1.0
 * @since 2021-11-17
 */
public class ColouredProperty extends Property {

    /**
     * This enum contains the different states that the coloured property can be in with respect to houses and hotels
     */
    public enum HouseHotel {
        NONE(0), HOUSE1(1), HOUSE2(2), HOUSE3(3), HOUSE4(4), HOTEL(5);
        private static HouseHotel[] values = values();
        private final int num;
        HouseHotel(int num) { this.num = num; }
        public int getNum() { return this.num; }
        private HouseHotel next() {
            return values[(this.ordinal() + 1) % values.length];
        }
    }

    /**
     * The price of a house or hotel for this property
     */
    private int houseHotelPrice;

    /**
     * The size of the colour group this property is a member of
     */
    private int setSize;

    /**
     * The current status of the coloured property with respect to houses and hotels
     */
    private HouseHotel propertyHouseHotelStatus;

    /**
     * The JPanel that contains houses and hotels
     */
    private JPanel houseHotelPanel;

    private String colorHex;


    public ColouredProperty() {
        this("", -1, "", "", "", -1, -1, -1);
    }

    /**
     * The ColouredProperty constructor
     *
     * @param name  String, The name of the property
     * @param price int, The price of the property
     * @param type String, type of property
     * @param color String, The color of the property
     */
    public ColouredProperty(String name, int price, String type, String color, String path, int position, int houseHotelPrice, int setSize) {
        super(name, price, type, color, path, position);
        this.houseHotelPrice = houseHotelPrice;
        this.setSize = setSize;
        this.propertyHouseHotelStatus = HouseHotel.NONE;

        houseHotelPanel = new JPanel(new GridLayout(1,4));
        houseHotelPanel.setPreferredSize(new Dimension(80,20));
        this.add(houseHotelPanel, BorderLayout.PAGE_START);
    }

    /**
     * Get the price of rent for the property
     *
     * @return int, the price of rent
     */
    public int getRent() {
        int rent = 0;
        if (propertyHouseHotelStatus == HouseHotel.NONE) {
            rent = (int) Math.round(Math.pow(price, 3) * 0.000001 + Math.pow(price, 2) * -0.0007 + price * 0.2014 - 7.5593);
        } else if (propertyHouseHotelStatus == HouseHotel.HOUSE1) {
            rent = (int) Math.round(Math.pow(price, 4) * -0.000000004 + Math.pow(price, 3) * 0.000005 + Math.pow(price, 2) * -0.0012 + price * 0.5665 - 16.601);
        } else if (propertyHouseHotelStatus == HouseHotel.HOUSE2) {
            rent = (int) Math.round(Math.pow(price, 4) * -0.00000001 + Math.pow(price, 3) * 0.00001 + Math.pow(price, 2) * -0.0025 + price * 1.3893 -33.691);
        } else if (propertyHouseHotelStatus == HouseHotel.HOUSE3) {
            rent = (int) Math.round(Math.pow(price, 4) * 0.0000002 + Math.pow(price, 3) * -0.0001 + Math.pow(price, 2) * 0.0339 + price * 0.3361 + 18.22);
        } else if (propertyHouseHotelStatus == HouseHotel.HOUSE4) {
            rent = (int) Math.round(Math.pow(price, 4) * 0.0000002 + Math.pow(price, 3) * -0.0002 + Math.pow(price, 2) * 0.0357 + price * 1.6338 + 39.976);
        } else {
            rent = (int) Math.round(Math.pow(price, 4) * 0.0000003 + Math.pow(price, 3) * -0.0002 + Math.pow(price, 2) * 0.0518 + price * 0.9426 + 150.03);
        }
        return rent;
    }

    /**
     * A getter for the size of the colour set that this coloured property is a member of
     *
     * @return int, the size of the coloured set
     */
    public int getSetSize() {
        return setSize;
    }

    /**
     * A getter for the price of a house or hotel for this coloured property
     *
     * @return int, the price of a house or hotel
     */
    public int getHouseHotelPrice() {
        return houseHotelPrice;
    }

    /**
     * The status of this coloured property in terms of houses and hotels
     *
     * @return HouseHotel, the status of this coloured property (1 house, 2 houses, etc.)
     */
    public HouseHotel getHouseHotelStatus() {
        return this.propertyHouseHotelStatus;
    }

    /**
     * Adds a house/hotel icon to the board space
     */
    public void addHouseHotel() {
        if (propertyHouseHotelStatus.getNum() < 4) {
            //ImageIcon tempHouseIcon = new ImageIcon("images/house.png");
            ImageIcon tempHouseIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/house.png")));

            JLabel houseIcon = new JLabel(new ImageIcon(tempHouseIcon.getImage().getScaledInstance(20,20,Image.SCALE_FAST)));
            houseHotelPanel.add(houseIcon);
        } else {
            //ImageIcon tempHotelIcon = new ImageIcon("images/hotel.png");
            ImageIcon tempHotelIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/hotel.png")));
            JLabel hotelIcon = new JLabel(new ImageIcon(tempHotelIcon.getImage().getScaledInstance(20,20,Image.SCALE_FAST)));
            houseHotelPanel.removeAll();
            houseHotelPanel.add(hotelIcon);
        }
        propertyHouseHotelStatus = propertyHouseHotelStatus.next();
    }

    /**
     * Resets the houses/hotel for this coloured property to none and removes all icons from the board
     */
    public void resetHouseHotel() {
        propertyHouseHotelStatus = HouseHotel.NONE;
        houseHotelPanel.removeAll();
    }

    /**
     *
     * @param houseHotelPrice
     */
    public void setHouseHotelPrice(int houseHotelPrice) {
        this.houseHotelPrice = houseHotelPrice;
    }

    /**
     *
     * @param setSize
     */
    public void setSetSize(int setSize) {
        this.setSize = setSize;
    }

    /**
     *
     * @param colorHex
     */
    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
        houseHotelPanel.setBackground(Color.decode(colorHex));
    }

    /**
     * The equals method for a coloured property object
     *
     * @param o Object, the object to be compared with the object this method is called on
     * @return boolean, where it is equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ColouredProperty that = (ColouredProperty) o;
        return houseHotelPrice == that.houseHotelPrice && Objects.equals(color, that.color) && propertyHouseHotelStatus == that.propertyHouseHotelStatus;
    }

    /**
     * Formats information about this instance of coloured property in a String
     *
     * @return String, info about this coloured property
     */
    @Override
    public String displayInfo() {
        String text = "\nYou are now located at: " + getName();
        if (isAvailable){
            text += "\nThis property costs $" + getPrice();
        }
        else{
            text += "\nThe rent for this property is $" + getRent() + ".";
        }
        return text;
    }

    /**
     * Returns a String representation of the Property object
     *
     * @return String, the property object as a string value
     */
    @Override
    public String toString() {
        return "Name: " + this.getName() + ", Color: " + this.getColor() + ", Price: " + this.getPrice();
    }
}