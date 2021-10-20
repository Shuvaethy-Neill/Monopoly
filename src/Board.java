import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

    // Under construction
    public boolean displayBoard() {
        String command = "";
        Scanner sc = new Scanner(System.in);
        while(!command.equalsIgnoreCase("")) {
            // fix ^
            System.out.println("Please enter a command to start the game >>>");
            System.out.println("Type 'help' for a list of Monopoly commands");
            command = sc.nextLine();
            if(command.equalsIgnoreCase("help")) {
                System.out.println("Type 'instructions' to view the Monopoly Game instructions");
                System.out.println("Type 'rules' to view the Monopoly Game rules");
                System.out.println("Type 'roll' to roll a dice when it is your turn to play");
                System.out.println("Type 'state' to view your state in the Monopoly Game");
                System.out.println("Type 'buy' to purchase a property");
                System.out.println("Type 'pass' to pass your turn over to the next player");
                // More commands added maybe?
            }
            else if(command.equalsIgnoreCase("instructions")) {
                System.out.println(""); // Print out a list of instructions for the player
            }
            else if(command.equalsIgnoreCase("rules")) {
                System.out.println(""); // Print out a list of rules for the player
            }

            else if(command.equalsIgnoreCase("roll")) {
                dice.roll();
                System.out.println("The value of your dice is: " + dice.getRollValue());
                // advances the player based on dice value on the board
            }
            else if(command.equalsIgnoreCase("state")) {

            }
            else if(command.equalsIgnoreCase("buy")) {

            }
            else if(command.equalsIgnoreCase("pass")) {

            }
            else {
                System.out.println("ERROR: Please enter a valid command.\n");
            }
        }
        return true;
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