import java.util.Scanner;

/**
 *
 */
public class Board {
    private Dice dice;
    private BoardSpace[] pieces;
    private int position;
    private enum boardSquares {
        MEDITERRANEAN_AVENUE("MEDITERRANEAN AVENUE",60,"brown"),
        BALTIC_AVENUE("BALTIC AVENUE",60,"brown"),
        INCOME_TAX("INCOME TAX", 200),
        ORIENTAL_AVENUE("ORIENTAL AVENUE",100,"light blue"),
        VERMONT_AVENUE("VERMONT AVENUE",100,"light blue"),
        CONNECTICUT_AVENUE("CONNECTICUT AVENUE",120,"light blue"),
        ST_CHARLES_PLACE("ST. CHARLES PLACE",140,"magenta"),
        STATES_AVENUE("STATES AVENUE",140,"magenta"),
        VIRGINIA_AVENUE("VIRGINIA AVENUE",160,"magenta"),
        ST_JAMES_PLACE("ST. JAMES PLACE",180,"orange"),
        TENNESSEE("TENNESSEE",180,"orange"),
        NEW_YORK_AVENUE("NEW YORK AVENUE",200,"orange"),
        FREE_PARKING("FREE PARKING"),
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
        LUXURY_TAX("LUXURY TAX", 100),
        BOARDWALK("BOARDWALK",400,"blue");

        private BoardSpace boardSpace;

        boardSquares(String name, int price , String color) {
          this.boardSpace = new Property(name, price, color);
        }
        boardSquares(String name, int cost) {
            this.boardSpace = new Tax(name, cost);
        }
        boardSquares(String name) {
            this.boardSpace = new FreeParking(name);
        }
    }

    /**
     * Constructor for the Board Class
     */
    public Board(){
        dice = new Dice();
        pieces = new BoardSpace[25];
        position = 0;
        for (boardSquares s : boardSquares.values()) {
            if (position == 25){continue;}
            else {
                pieces[position] = s.boardSpace;
            }
            position += 1;
        }
    }

    /**
     *
     * @return
     */
    public BoardSpace[] getPieces() {
        return pieces;
    }

    /**
     *
     * @param i
     * @return
     */
    public BoardSpace getP(int i){
        return pieces[i];
    }

    /**
     * Method displays the user interface of the Monopoly Board that takes user input
     */
    public void play() {
        System.out.println("Welcome to Monopoly!");
        System.out.println("Enter a command, type 'help' for a list of commands");
        System.out.println(">>>");
        Scanner sc = new Scanner(System.in);
        String command = "";
        command = sc.nextLine();
        boolean exit = false;

        while(!command.equalsIgnoreCase("quit")) {
            if(command.equalsIgnoreCase("help")) {
                System.out.println("Type 'start' to start the game");
                System.out.println("Type 'state' to view your... like stats??");
                System.out.println("Type 'roll' to roll dices on your turn");
                System.out.println("Type 'buy' to purchase a property");
                System.out.println("Type 'pass' to pass your turn to the next player");
                System.out.println("Type 'quit' to quit the game");
            }
            else if(command.equalsIgnoreCase("state")) {
            }
            else if (command.equalsIgnoreCase("start")){
                System.out.println("Let's begin by rolling the dices");
                System.out.println("You rolled: " + dice.toString());
                System.out.println("You will move up " + dice.getRollValue() + "spaces on the board!");
            }
            else if(command.equalsIgnoreCase("roll")) {
                System.out.println("Rolling the dices:");
                System.out.println("You rolled: " + dice.toString());
                System.out.println("You will move up " + dice.getRollValue() + "spaces on the board!");
            }
            else {
                System.out.println("Error: Please enter a valid command");
                break;
            }
        }
    }

    public static void main(String[] args) {
        Dice d = new Dice();
        d.roll();
        System.out.println(d.getRollValue() + " " + d.isDouble());
        System.out.println(d);
        Board b = new Board();
        b.play();

    }
}