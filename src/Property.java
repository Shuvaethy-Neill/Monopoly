public class Property {
    private boolean isAvailable;
    private boolean mortgaged;
    private String name;
    private int price;
    private String color;

    public Property(String name, int price, String color) {
        this.isAvailable = true;
        this.mortgaged = false;
        this.name = name;
        this.price = price;
        this.color = color;
    }
}


