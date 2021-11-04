import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

/**
 * The Board Class that contains the user interface of the Monopoly board
 *
 * @author Harismran Kanwar, Dorothy Tran, and Shuvaethy Neill
 * @version 1.0
 * @since 2021-10-24
 */
public class MonopolyModel {

    /**
     *
     */
    private List<MonopolyView> monopolyViews;

    /**
     *
     */
    private Random rand;

    /**
     *
     */
    private Dice dice;

    /**
     *
     */
    private BoardSpace[] pieces;

    /**
     *
     */
    private int position;

    /**
     *
     */
    private int player; // current player

    /**
     *
     */
    private ArrayList<Player> players;

    /**
     * Constructor for the Board Class
     */
    public MonopolyModel(){
        rand = new Random();
        dice = new Dice();
        pieces = new BoardSpace[25];
        position = 0;
        player = 0;
        for (BoardSquares s : BoardSquares.values()) {
            if (position == 25){continue;}
            else {
                pieces[position] = s.boardSpace;
            }
            position += 1;
        }
        players = new ArrayList<>();

        monopolyViews = new ArrayList<>();
    }

    /**
     *
     * @param view
     */
    public void addView(MonopolyView view) {
        this.monopolyViews.add(view);
    }

    /**
     *
     * @param viewIndex
     */
    public void removeView(int viewIndex) {
        this.monopolyViews.remove(viewIndex);
    }

    /**
     *
     */
    public void notifyViews() {
        for (MonopolyView v : monopolyViews) {
            v.update();
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    // Every thing above this works for MVC



    /**
     * Method gets the board space pieces
     *
     * @return BoardSpace[], The array of BoardSpace pieces
     */
    public BoardSpace[] getPieces() {
        return pieces;
    }

    /**
     * Method gets the BoardSpace piece position
     * @param i, the index of the BoardSpace piece
     * @return BoardSpace, the board space piece
     */
    public BoardSpace getP(int i){
        return pieces[i];
    }

    /**
     * Method displays text on the console to begin the Monopoly game
     */
    private void welcome() {
        int people = 0;
        boolean validInput = false;
        System.out.println("=============================\nWelcome to the Monopoly Game!\n=============================");

        do {
            System.out.println("How many players are playing? Enter 2, 3, or 4 (Minimum:2, Maximum:4)");
            System.out.print(">>> ");
            Scanner sc = new Scanner(System.in);
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

        if (pieces[players.get(player).getPosition()] instanceof Tax) {
            check = players.get(player).isBankrupt(((Tax) pieces[players.get(player).getPosition()]).getCost());
        }
        else if (pieces[players.get(player).getPosition()] instanceof Property) {
            if (!((Property) pieces[players.get(player).getPosition()]).isAvailable()) { //property not available
                check = players.get(player).isBankrupt(((Property) pieces[players.get(player).getPosition()]).getRent());
            } else {
                check = (players.get(player).isBankrupt(((Property) pieces[players.get(player).getPosition()]).getPrice()));
            }
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
    private String endTurn(){
        player++;
        if(player > players.size()-1){
            player = 0;
        }
        System.out.println("\nPLAYER " + (player+1) + "'S TURN!");
        return "reset";
    }

    /**
     * Method displays the user interface of the Monopoly Board that takes user input
     */
    public void play() {
        boolean playing = false;
        boolean validInput = false;
        welcome();
        System.out.println("Enter a command...");
        System.out.println("Type 'help' for a list of commands");
        System.out.print(">>> ");
        Scanner sc = new Scanner(System.in);
        String command;
        command = sc.nextLine();
        String prevCommand = command;

        while (true) {
            // If the player is bankrupt, end their turn
            if(checkBankruptcy()){
                command = endTurn();
            }
            else if(!playing && (!command.equalsIgnoreCase("start") && (!command.equalsIgnoreCase("help")))){
                System.out.println("Please start a game.");
            }

            // Prevents the player from inputting the same command more the once if the rolled dice values are not doubles
            else if (prevCommand.equalsIgnoreCase(command) && !dice.isDouble() && command.equalsIgnoreCase("pass")){
               System.out.println("Invalid command");
                System.out.println("Please try again :)");
            }
            else if (command.equalsIgnoreCase("help")) {
                validInput = true;
                System.out.println("Type 'start' to start the game");
                System.out.println("Type 'state' to view your state in the game");
                System.out.println("Type 'roll' to roll dices on your turn");
                System.out.println("Type 'buy' to purchase a property");
                System.out.println("Type 'pass' to pass your turn to the next player");
                System.out.println("Type 'quit' to end the game");
            }
            else if (command.equalsIgnoreCase("state")) {
                validInput = true;
                System.out.println(players.get(player).toString());
            }
            else if (command.equalsIgnoreCase("buy")) {
                validInput = true;

                // Notifies the player that they must roll first when it is their turn to play if they input a different command
                if (prevCommand.equalsIgnoreCase("reset")){
                    System.out.println("Oops you haven't rolled! Type 'roll' to roll the dice!");
                }

                // The situation when the player purchases a property
                else if(pieces[players.get(player).getPosition()] instanceof Property && prevCommand.equalsIgnoreCase("roll")){
                    if (((Property) pieces[players.get(player).getPosition()]).isAvailable()) {
                        players.get(player).doTransaction(((Property) pieces[players.get(player).getPosition()]).getPrice()); // price of property is deducted from player's account
                        players.get(player).addProperty(((Property) pieces[players.get(player).getPosition()])); // property is added to player's account
                        ((Property) pieces[players.get(player).getPosition()]).purchase(); //set property to unavailable
                        ((Property) pieces[players.get(player).getPosition()]).setOwner(players.get(player)); //set owner
                        System.out.println("Successfully purchased!");
                    }
                } else {
                    System.out.println("Unfortunately the property you are on is not available for purchase.");
                }

                if (!dice.isDouble() && prevCommand.equalsIgnoreCase("roll")) {
                    command = endTurn();
                }

            }
            else if (command.equalsIgnoreCase("start")){
                validInput = true;
                if(!playing) {
                    //notify user that game is starting
                    System.out.println("\nGreat! I will choose which player will go first!");
                    player = rand.nextInt(players.size()); // randomly selects a player
                    System.out.println("Player " + (player + 1) + " will start");
                    playing = true;
                }
                else{
                    System.out.println("The game has already started. Please enter a valid command for your turn.");
                }
            }
            else if (command.equalsIgnoreCase("roll")){
                validInput = true;
                // Error handling if the current player inputs the roll command multiple times if their rolled dice were not doubles
                if((players.get(player).getNumDoublesRolled() == 0) && prevCommand.equalsIgnoreCase("roll")){
                    System.out.println("Invalid! You already rolled!");
                }
                else {
                    System.out.println("Rolling the dice:");
                    dice.roll();
                    if (players.get(player).getNumDoublesRolled() == 3) {
                        command = endTurn();
                    } // If 3 doubles rolled end turn
                    if (dice.isDouble()) {
                        players.get(player).incrementNumDoublesRolled();
                    }

                    System.out.println("You rolled: " + dice.toString());
                    System.out.println("You will move up " + dice.getRollValue() + " spaces on the board!");
                    players.get(player).move(dice.getRollValue());
                    players.get(player).setPositionName(pieces[players.get(player).getPosition()].toString()); //tell player where they are located
                    pieces[players.get(player).getPosition()].displayInfo();

                    if (pieces[players.get(player).getPosition()] instanceof FreeParking) {
                        if (!dice.isDouble()) {
                            command=endTurn(); // Player lands on the Free Parking space, end their turn if doubles are rolled
                        }
                    } else if (pieces[players.get(player).getPosition()] instanceof Tax) {
                        players.get(player).doTransaction(((Tax) pieces[players.get(player).getPosition()]).getCost());
                        if (!dice.isDouble()) {
                            command=endTurn(); // Player lands on the Tax space, end their turn if doubles are rolled
                        }
                    } else if (!((Property) pieces[players.get(player).getPosition()]).isAvailable()) { // Property is not available
                        // Player who owns the property gets rent
                        if (((Property) pieces[players.get(player).getPosition()]).getOwner().equals(players.get(player))) { // If the player lands on themselves, do nothing
                            System.out.println("You do not need to pay rent since you own this property.");
                        } else {
                            System.out.println("Taking the money from your account");
                            players.get(player).doTransaction(((Property) pieces[players.get(player).getPosition()]).getRent()); // Deducts the cost from account
                            ((Property) pieces[players.get(player).getPosition()]).getOwner().setMoney(((Property) pieces[players.get(player).getPosition()]).getRent()); // adds the rent cost to the owner's account
                        }
                        if (!checkBankruptcy() && !dice.isDouble()) {
                            command=endTurn();
                        }
                    }
                }
            }
             else if (command.equalsIgnoreCase("pass")) {
                 validInput = true;
                 System.out.println("Your turn is now over! Passing to next player.");
                 command=endTurn();
            } else if (command.equalsIgnoreCase("quit")) {
                System.out.println("Thanks for playing! See you next time :)");
                System.exit(0);
            } else {
                 validInput= false;
                System.out.println("Error: Please enter a valid command");
            }
            if (validInput){prevCommand = command;} //update previous command
            System.out.println("");
            System.out.println("Player " + (player + 1) + ":");
            System.out.print("Enter a command >>> ");
            command = sc.nextLine();
        }
    }
}