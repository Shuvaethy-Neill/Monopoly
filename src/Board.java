import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 */
public class Board {
    private Dice dice;
    private BoardSpace[] pieces;
    private int position;
    private int player; //curent player
   ArrayList<Player> players;

    private enum boardSquares {
        START("START"), //exception for starting purposes
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
    public Board(String name){
        dice = new Dice();
        pieces = new BoardSpace[25];
        position = 0;
        player = 0;
        for (boardSquares s : boardSquares.values()) {
            if (position == 25){continue;}
            else {
                pieces[position] = s.boardSpace;
            }
            position += 1;
        }
        players = new ArrayList<>();
        Player player1 = new Player(name);
        Player player2 = new Player("P2");
        players.add(player1);
        players.add(player2);
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

    private boolean checkBankruptcy(){
        if(pieces[players.get(player).getPosition()].getType().equals("free parking")) {return false;}

        else if (pieces[players.get(player).getPosition()].getType().equals("tax")) {
            return players.get(player).isBankrupt(((Tax) pieces[players.get(player).getPosition()]).getCost());
        }
        else if (!((Property) pieces[players.get(player).getPosition()]).isAvailable()){ //property not available
            return players.get(player).isBankrupt(((Property) pieces[players.get(player).getPosition()]).getRent());

        }
        else if (((Property) pieces[players.get(player).getPosition()]).isAvailable()){
            return players.get(player).isBankrupt(((Property) pieces[players.get(player).getPosition()]).getPrice());
        }


        return false;
    }

    /**
     * Method displays the user interface of the Monopoly Board that takes user input
     */
    public void play() {
        System.out.println("Welcome to the Monopoly Game!");
        System.out.println("Enter a command, type 'help' for a list of commands");
        System.out.print(">>> ");
        Scanner sc = new Scanner(System.in);
        String command = "";
        command = sc.nextLine();
        boolean exit = true;


        while(true) {
            if(command.equalsIgnoreCase("help")) {
                System.out.println("Type 'start' to start the game");
                System.out.println("Type 'state' to view your... like stats??");
                System.out.println("Type 'roll' to roll dices on your turn");
                System.out.println("Type 'buy' to purchase a property");
                System.out.println("Type 'pass' to pass your turn to the next player");
                System.out.println("Type 'quit' to quit the game");
            }

            if(checkBankruptcy()){
                System.out.println("You have reached bankruptcy :(");
                System.out.println("You are being eliminated from the game");
                players.remove(player);
                if (players.size() <2){
                   command = "pass";
                }
            }
            else if(command.equalsIgnoreCase("state")) {
                System.out.println(players.get(player).toString());
            }
            else if(command.equalsIgnoreCase("buy")) {
                if (((Property)pieces[players.get(player).getPosition()]).isAvailable()){
                    players.get(player).doTransaction(((Property)pieces[players.get(player).getPosition()]).getPrice());
                    players.get(player).addProperty(((Property)pieces[players.get(player).getPosition()]));
                    ((Property)pieces[players.get(player).getPosition()]).purchase();
                    System.out.println(players.get(player).toString()); //for testing rn
                }
                else{
                    System.out.println("Unfortunately the property is no longer available for purchase.");
                }

            }
            else if(command.equalsIgnoreCase("start") || command.equalsIgnoreCase("roll") || command.equalsIgnoreCase("next")) {
                //notify user that game is starting
                if(command.equalsIgnoreCase("start")){System.out.println("Let's begin by rolling the dices!");}

                System.out.println("Rolling the dices:");
                dice.roll();
                System.out.println("You rolled: " + dice.toString());
                System.out.println("You will move up " + dice.getRollValue() + " spaces on the board!");
                players.get(player).move(dice.getRollValue());
                pieces[players.get(player).getPosition()].displayInfo();
                if(pieces[players.get(player).getPosition()].getType().equals("free parking")){
                    players.get(player).setMoney(((FreeParking)pieces[players.get(player).getPosition()]).getAmount());
                }
                else if(pieces[players.get(player).getPosition()].getType().equals("tax")){
                    players.get(player).doTransaction(((Tax)pieces[players.get(player).getPosition()]).getCost());
                    ((FreeParking)pieces[13]).addAmount(((Tax)pieces[players.get(player).getPosition()]).getCost()); //add tax to parking
                }
                else if (!((Property)pieces[players.get(player).getPosition()]).isAvailable()){ //property is not available
                    players.get(player).doTransaction(((Property) pieces[players.get(player).getPosition()]).getRent());
                    //the player who owns the property gets rent
                    ((Property) pieces[players.get(player).getPosition()]).getOwner().setMoney(((Property) pieces[players.get(player).getPosition()]).getRent()); //failing

                }
            }
            else if(command.equalsIgnoreCase("pass")) {
                System.out.println("Your turn is now over! Passing to next player.");
                player++;
                if(player > players.size()-1){
                    player = 0;
                }
            }
            else if(command.equalsIgnoreCase("quit")) {
                System.out.println("Thanks for playing! See you next time :)");
                System.exit(0);
            }
            else {
                System.out.println("Error: Please enter a valid command");
            }
            System.out.println(" ");
            System.out.print("Please enter a command >>> ");
            command = sc.nextLine();
            }
        }


    public static void main(String[] args) {
        System.out.print("Please enter your name to begin : ");
        Scanner sc = new Scanner(System.in);
        String name = "";
        name = sc.nextLine();
        Board b = new Board(name);
        b.play();

    }
}