import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

//welcome error handling (works except for this): type 2.3 then 1 then 2.3 again. i'm lost on why it prints try again twice after the second 2.3
//should we be creating two instances of scanners or just have one outside of both methods?
//i think start should become invalid once the game has started? if you type help then start,it rolls (fix might be duplicate code though since start and roll use the same code OR we could just make roll a method and invoke it for both commands)
//should roll be invalid before the game starts? because if you enter roll then it doesnt choose the starting player randomly, it starts with P1
//if you land on a property that you can buy but enter roll instead it lets you roll again
//if you enter buy before starting you get an error
//state gives position number not name of square


/**
 * The Board Class that contains the user interface of the Monopoly board
 * @author
 * @version 1.0
 */
public class Board {
    private Random rand;
    private Dice dice;
    private BoardSpace[] pieces;
    private int position;
    private int player; // current player
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
    public Board(){
        rand = new Random();
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
     * Method displays text on the console to begin the Monopoly game
     */
    private void welcome() {
        Scanner sc = new Scanner(System.in);
        int people = 0;
        boolean validInput = false;
        System.out.println("=============================\nWelcome to the Monopoly Game!\n=============================");

        do {
            System.out.println("How many players are playing? Enter 2, 3, or 4 (Minimum:2, Maximum:4)");
            System.out.print(">>> ");
            if (sc.hasNextInt()) {
                people = sc.nextInt();
                if((people > 1 && people < 5)) {
                    validInput = true;
                }
            }
            else{
                System.out.println("Sorry that is not an integer. Try again!");
                sc.nextLine();
            }
        }
        while (!validInput);

        for(int i = 0; i < people; i++){
            players.add(new Player("Player " + (i + 1)));
        }
    }

    /**
     * Method checks if the Player is bankrupt
     * @return boolean, true if they are bankrupt, false otherwise
     */
    private boolean checkBankruptcy(){
        boolean check = false;
        if(pieces[players.get(player).getPosition()].getType().equals("free parking")) {}

        else if (pieces[players.get(player).getPosition()].getType().equals("tax")) {
            check = players.get(player).isBankrupt(((Tax) pieces[players.get(player).getPosition()]).getCost());
        }
        else if (!((Property) pieces[players.get(player).getPosition()]).isAvailable()){ //property not available
            check = players.get(player).isBankrupt(((Property) pieces[players.get(player).getPosition()]).getRent());

        }
        else if (((Property) pieces[players.get(player).getPosition()]).isAvailable()){
            check = players.get(player).isBankrupt(((Property) pieces[players.get(player).getPosition()]).getPrice());
        }
        if (check) {
            System.out.println("You have reached bankruptcy :(");
            System.out.println("You are being eliminated from the game");
            for(int i = 0; i < players.get(player).getProperties().size(); i++) {
                players.get(player).getProperties().get(i).sell();
            }
            players.remove(player);
            if (players.size() == 1){
                System.out.println("GAME OVER! " + players.get(0).getName() + " has won the game! ");
                System.out.println("Thanks for playing!");
                System.exit(0);
            }
        }
        return check;
    }

    /**
     * Method passes the turn to the next player in the game
     */
    private void endTurn(){
        player++;
        if(player > players.size()-1){
            player = 0;
        }
        System.out.println("\nPLAYER " + (player+1) + "'S TURN!");
    }

    /**
     * Method displays the user interface of the Monopoly Board that takes user input
     */
    public void play() {
        welcome();
        System.out.println("Enter a command...");
        System.out.println("Type 'help' for a list of commands");
        System.out.print(">>> ");
        Scanner sc = new Scanner(System.in);
        String command = "";
        command = sc.nextLine();
        String prevCommand = command;
        while (true) {
            if(checkBankruptcy()){
                //if bankrupt then it will exit
                endTurn();
            }
            // MUST FIX:
            // if ((prevCommand.equalsIgnoreCase(command)||prevCommand.equalsIgnoreCase("start")) && !dice.isDouble())
            // ^^ This line prevents rolling after start but you cant buy or pass or other commands
            if (prevCommand.equalsIgnoreCase(command) && !dice.isDouble()){
                System.out.println("Invalid command");
                System.out.println("Please try again :)");
                //endTurn();
                // Should their turn end immediately or give them the chance to buy the property?
                // ^ this assumes the previous command was the same, so let them try again?
            }
            else if (command.equalsIgnoreCase("help")) {
                System.out.println("Type 'start' to start the game");
                System.out.println("Type 'state' to view your state in the game");
                System.out.println("Type 'roll' to roll dices on your turn");
                System.out.println("Type 'buy' to purchase a property");
                System.out.println("Type 'pass' to pass your turn to the next player");
                System.out.println("Type 'quit' to end the game");
            }
            else if (command.equalsIgnoreCase("state")) {
                System.out.println(players.get(player).toString());
            } else if (command.equalsIgnoreCase("buy")) {
                if (((Property) pieces[players.get(player).getPosition()]).isAvailable()) {
                    players.get(player).doTransaction(((Property) pieces[players.get(player).getPosition()]).getPrice());
                    players.get(player).addProperty(((Property) pieces[players.get(player).getPosition()]));
                    ((Property) pieces[players.get(player).getPosition()]).purchase(); //set property to unavailable
                    ((Property) pieces[players.get(player).getPosition()]).setOwner(players.get(player)); //set owner
                    System.out.println(players.get(player).toString()); //for testing rn
                } else {
                    System.out.println("Unfortunately the property is no longer available for purchase.");
                    endTurn();
                }
                if (!dice.isDouble()) {
                    endTurn();
                }

            } else if (command.equalsIgnoreCase("start") || command.equalsIgnoreCase("roll")) {
                //notify user that game is starting
                if (command.equalsIgnoreCase("start")) {
                    System.out.println("Great! I will choose which player will go first!\n");
                    player = rand.nextInt(players.size());
                    System.out.println("Player " + (player + 1) + " will start");
                    System.out.println("Let's begin by rolling the dice!\n");
                }

                System.out.println("Rolling the dice:");
                dice.roll();
                if(players.get(player).getNumDoublesRolled() == 3){endTurn();} //if 3 doubles rolled end turn
                if (dice.isDouble()){players.get(player).incrementNumDoublesRolled();}

                System.out.println("You rolled: " + dice.toString());
                System.out.println("You will move up " + dice.getRollValue() + " spaces on the board!");
                players.get(player).move(dice.getRollValue());
                pieces[players.get(player).getPosition()].displayInfo();

                if (pieces[players.get(player).getPosition()].getType().equals("free parking")) {
                    if (!dice.isDouble()){endTurn();}
                } else if (pieces[players.get(player).getPosition()].getType().equals("tax")) {
                    players.get(player).doTransaction(((Tax) pieces[players.get(player).getPosition()]).getCost());
                    ((FreeParking) pieces[13]).addAmount(((Tax) pieces[players.get(player).getPosition()]).getCost()); //add tax to parking
                    if (!dice.isDouble()){endTurn();}
                } else if (!((Property) pieces[players.get(player).getPosition()]).isAvailable()) { //property is not available
                    players.get(player).doTransaction(((Property) pieces[players.get(player).getPosition()]).getRent());
                    //the player who owns the property gets rent
                    if (((Property) pieces[players.get(player).getPosition()]).getOwner().equals(players.get(player))){ //if the player lands on themselves, do nothing
                        continue;
                    }
                    else {
                        ((Property) pieces[players.get(player).getPosition()]).getOwner().setMoney(((Property) pieces[players.get(player).getPosition()]).getRent());
                    }
                    if (!checkBankruptcy() && !dice.isDouble()) {
                        endTurn();
                    }
                }
            } else if (command.equalsIgnoreCase("pass")) {
                System.out.println("Your turn is now over! Passing to next player.");
                endTurn();
            } else if (command.equalsIgnoreCase("quit")) {
                System.out.println("Thanks for playing! See you next time :)");
                System.exit(0);
            } else {
                System.out.println("Error: Please enter a valid command");
            }

            prevCommand=command; //update previous command
            System.out.println("");
            System.out.println("Player " + (player + 1) + ":");
            System.out.print("Enter a command >>> ");
            command = sc.nextLine();
        }
    }

    public static void main(String[] args) {
        Board b = new Board();
        b.play();

    }
}