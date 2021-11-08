import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The MonopolyModel Class that contains the MVC Model of the Monopoly board
 *
 * @author Harsismran Kanwar, Dorothy Tran, Shuvaethy Neill, and Evan Smedley
 * @version 1.0
 * @since 2021-10-24
 */
public class MonopolyModel {

    private List<MonopolyView> monopolyViews;

    private Random rand;

    private Dice dice;

    private BoardSpace[] pieces;

    private int position;

    private int player; // current player

    private ArrayList<Player> players;

    private String outputText; //text to notify user of decisions made

    public enum Status {UNDECIDED,PLAYING, BANKRUPT, BANKRUPT2};
    private Status playerStatus;

    /**
     * Constructor for the Board Class
     */
    public MonopolyModel() {
        rand = new Random();
        dice = new Dice();
        pieces = new BoardSpace[BoardSquares.values().length];
        position = 0;
        player = 0;
        playerStatus = Status.UNDECIDED;
        outputText="";
        for (BoardSquares s : BoardSquares.values()) {
            if (position == BoardSquares.values().length) {
                continue;
            } else {
                pieces[position] = s.boardSpace;
            }
            position += 1;
        }
        players = new ArrayList<>();

        monopolyViews = new ArrayList<>();
    }

    /**
     * @param view
     */
    public void addView(MonopolyView view) {
        this.monopolyViews.add(view);
    }

    /**
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
            v.update(new MonopolyEvent(this));
        }
    }

    /**
     * add player to the game
     * @param player
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**Method that retrives list of players
     * @return players
     */
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public int getCurrentPlayer(){
        return this.player;
    }

    // Every thing above this works for MVC

    public Dice getDice(){
        return this.dice;
    }

    public String getOutputText() {
        return outputText;
    }

    public Status getPlayerStatus() {
        return playerStatus;
    }

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
     *
     * @param i, the index of the BoardSpace piece
     * @return BoardSpace, the board space piece
     */
    public BoardSpace getP(int i) {
        return pieces[i];
    }

    public String start() {
        player = rand.nextInt(players.size());
        System.out.println("Starting the game...\n" + players.get(player).getName() + " will start!");
        notifyViews();
        return players.get(player).getName();
    }

    private void help(){
        outputText = "Help:" +"\n" + "Press the 'Roll Dice' button to roll dices on your turn"+"\n" +
                "Press the 'Buy Property' button to purchase a property" + "\n" +
                "Press the 'Pass' button to pass your turn to the next player"  + "\n" +
                "Press the 'x' on the game frame to end and close the game";
    }

    public void roll() {
        this.playerStatus = Status.PLAYING;
        dice.roll();
        if (players.get(player).getNumDoublesRolled() == 2) {
            endTurn();
        } // If 3 doubles rolled end turn
        if (dice.isDouble()) {
            players.get(player).incrementNumDoublesRolled();
        }

       outputText= "\n Rolling the Dices! You rolled : " + dice.toString() +
                    "\n You will move up " + dice.getRollValue() + " spaces on the board!";
        players.get(player).move(dice.getRollValue());
        players.get(player).setPositionName(pieces[players.get(player).getPosition()].toString()); //tell player where they are located
        outputText += pieces[players.get(player).getPosition()].displayInfo();
        if (pieces[players.get(player).getPosition()] instanceof FreeParking) {
            if (!dice.isDouble()) {
                //command = endTurn(); // Player lands on the Free Parking space, end their turn if doubles are rolled
                endTurn();
            }
        } else if (pieces[players.get(player).getPosition()] instanceof Tax) {
            players.get(player).doTransaction(((Tax) pieces[players.get(player).getPosition()]).getCost());
            if (!dice.isDouble()) {
                endTurn();
            }
        } else if (!((Property) pieces[players.get(player).getPosition()]).isAvailable()) { // Property is not available
            // Player who owns the property gets rent
            if (((Property) pieces[players.get(player).getPosition()]).getOwner().equals(players.get(player))) { // If the player lands on themselves, do nothing
                outputText+="\n You do not need to pay rent since you own this property.";
            } else {
                outputText+="\n Taking the money from your account";
                players.get(player).doTransaction(((Property) pieces[players.get(player).getPosition()]).getRent()); // Deducts the cost from account
                ((Property) pieces[players.get(player).getPosition()]).getOwner().setMoney(((Property) pieces[players.get(player).getPosition()]).getRent()); // adds the rent cost to the owner's account
            }
            if (!checkBankruptcy() && !dice.isDouble()) {
                //command = endTurn();
                endTurn();
            }

        }

    }

    /**
     * Method checks if the Player is bankrupt
     *
     * @return boolean, true if they are bankrupt, false otherwise
     */
    private boolean checkBankruptcy() {
        boolean check = false;

        if (pieces[players.get(player).getPosition()] instanceof Tax) {
            check = players.get(player).isBankrupt(((Tax) pieces[players.get(player).getPosition()]).getCost());
        } else if (pieces[players.get(player).getPosition()] instanceof Property) {
            if (!((Property) pieces[players.get(player).getPosition()]).isAvailable()) { //property not available
                check = players.get(player).isBankrupt(((Property) pieces[players.get(player).getPosition()]).getRent());
            } else {
                check = (players.get(player).isBankrupt(((Property) pieces[players.get(player).getPosition()]).getPrice()));
            }
        }

        if (check) {
            outputText="You have reached bankruptcy :( \n You are being eliminated from the game ";
            for (int i = 0; i < players.get(player).getProperties().size(); i++) {
                players.get(player).getProperties().get(i).sell();
            }
        }
        return check;
    }

    private void buy() {
        if (((Property) pieces[players.get(player).getPosition()]).isAvailable()) {
            players.get(player).doTransaction(((Property) pieces[players.get(player).getPosition()]).getPrice()); // price of property is deducted from player's account
            players.get(player).addProperty(((Property) pieces[players.get(player).getPosition()])); // property is added to player's account
            ((Property) pieces[players.get(player).getPosition()]).purchase(); //set property to unavailable
            ((Property) pieces[players.get(player).getPosition()]).setOwner(players.get(player)); //set owner
            outputText="\n Successfully purchased!";
        }
        //button should be disabled at this point (have to check availability when player lands not when they click buy)
        else {
            outputText= "\nUnfortunately the property you are on is not available for purchase.";
        }
    }

    /**
     * Method passes the turn to the next player in the game
     */
    public void endTurn() {
        player++;
        if (player > players.size() - 1) {
            player = 0;
        }
        outputText += "\nPLAYER " + (players.get(player).getName()) + " 'S TURN!";
        this.playerStatus= Status.UNDECIDED;
    }

    /**
     * Method displays the user interface of the Monopoly Board that takes user input
     */
    public void play(String command) {

        // If the player is bankrupt, end their turn
        if (checkBankruptcy()) {
            if (players.size() == 2) {
                this.playerStatus = Status.BANKRUPT;
                players.remove(player);
                outputText += "GAME OVER! " + players.get(0).getName() + " has won the game! \n Thanks for playing! ";
            }
            else{
                players.remove(player--);
                this.playerStatus = Status.BANKRUPT2;
                player++;
                outputText += players.get(player).getName() + "'s turn!";
            }


        } else if (command.equals("Help")) {
            help();
        }
        else if (command.equals("Buy Property")) {
            // The situation when the player purchases a property
            if (pieces[players.get(player).getPosition()] instanceof Property) {
                buy();
            }
            if (!dice.isDouble()) {
                endTurn();
            }
        } else if (command.equals("Roll Dice")) {
            roll();
        } else if (command.equals("Pass")) {
            outputText="Your turn is now over! Passing to next player.";
            endTurn();
        } else if (command.equals("quit")) {
            System.out.println("Thanks for playing! See you next time :)");
            System.exit(0);
        }
        notifyViews();
    }
}