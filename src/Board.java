public class Board {
    public enum properties {
        MEDITERRANEAN_AVENUE("MEDITERRANEAN AVENUE",60,"brown"),

        ;

        properties(String name, int price , String colour) {
            Property(name,price,colour);
        }
    }

    public Board(){

    }


    public static void main(String[] args) {
        Dice d = new Dice();
        d.roll();
        System.out.println(d.getRollValue() + " " + d.isDouble());
        d.roll();
        System.out.println(d.getRollValue() + " " + d.isDouble());
        d.roll();
        System.out.println(d.getRollValue() + " " + d.isDouble());
        d.roll();
        System.out.println(d.getRollValue() + " " + d.isDouble());
        d.roll();
        System.out.println(d.getRollValue() + " " + d.isDouble());
        System.out.println(d);

    }
}