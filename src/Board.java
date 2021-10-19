import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class Board {
    private Dice dice;
    private Property[] pieces;
    private int position;
    private enum properties {
        MEDITERRANEAN_AVENUE("MEDITERRANEAN AVENUE",60,"brown"),
        BALTIC_AVENUE("BALTIC AVENUE",60,"brown"),
        ORIENTAL_AVENUE("ORIENTAL AVENUE",100,"light blue"),
        VERMONT_AVENUE("VERMONT AVENUE",100,"light blue"),
        CONNECTICUT_AVENUE("CONNECTICUT AVENUE",120,"light blue"),
        ST_CHARLES_PLACE("ST. CHARLES PLACE",140,"magenta"),
        STATES_AVENUE("STATES AVENUE",140,"magenta"),
        VIRGINIA_AVENUE("VIRGINIA AVENUE",160,"magenta"),
        ST_JAMES_PLACE("ST. JAMES PLACE",180,"orange"),
        TENNESSEE("TENNESSEE",180,"orange"),
        NEW_YORK_AVENUE("NEW YORK AVENUE",200,"orange"),
        KENTUCKY_AVENUE("KENTUCKY AVENUE",220,"red"),
        INDIANA_AVENUE("INDIANA AVENUE",220,"red"),
        ILLINOIS_AVENUE("ILLINOIS AVENUE",240,"red"),
        ATLANTIC_AVENUE("ATLANTIC AVENUE",260,"yellow"),
        VENTNOR_AVENUE("VENTNOR AVENUE",260,"yellow"),
        MARVIN_GARDENS("MARVIN GARDENS",280,"yellow"),
        PACIFIC_AVENUE("PACIFIC AVENUE",300,"green"),
        NORTH_CAROLINA_AVENUE("NORTH CAROLINA AVENUE",300,"green"),
        PENNSYLVANIA_AVENUE("PENNSYLVANIA AVENUE",320,"green"),
        PARK_PLACE("PARK PLACE",350,"blue"),
        BOARDWALK("BOARDWALK",400,"blue");

        private Property property;
        properties(String name, int price , String color) {
          this.property = new Property(name,price,color);
        }
    }

    public Board(){
        dice = new Dice();
        pieces = new Property[22];
        position=0;
        for (properties p : properties.values()) {
            if (position==22){continue;}
            else {
                pieces[position] = p.property;
            }
            position += 1;
        }


    }

    public Property[] getPieces() {
        return pieces;
    }

    public Property getP(int i){
        return pieces[i];
    }

    public static void main(String[] args) {
        Dice d = new Dice();
        d.roll();
        System.out.println(d.getRollValue() + " " + d.isDouble());
        System.out.println(d);
        Board b = new Board();

        for (int i =0; i < b.getPieces().length;i++){
            //System.out.println(b.getP(i).getName());
        }
    }
}