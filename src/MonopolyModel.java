import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * THINGS WE STILL NEED TO DO:
 * -make help and pass constants
 */

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

    public enum Status {UNDECIDED,PLAYING, BANKRUPT, BANKRUPT2, QUITTING};

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

    public int getPlayer() {
        return this.player;
    }

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
        if (dice.isDouble()) {
            players.get(player).incrementNumDoublesRolled();
        }
        if (players.get(player).getNumDoublesRolled() == 3) {
            endTurn();
        } // If 3 doubles rolled end turn

       outputText += "Rolling the Dices! You rolled : " + dice.toString() +
                    "\nYou will move up " + dice.getRollValue() + " spaces on the board!";
        players.get(player).move(dice.getRollValue());
        players.get(player).setPositionName(pieces[players.get(player).getPosition()].toString()); //tell player where they are located
        outputText += pieces[players.get(player).getPosition()].displayInfo();
        if(checkBankruptcy()){
            endTurn();
        }
        else if (pieces[players.get(player).getPosition()] instanceof FreeParking) {
            this.playerStatus = Status.UNDECIDED;
            if (!dice.isDouble()) {
                endTurn();
            }
        } else if (pieces[players.get(player).getPosition()] instanceof Tax) {
            players.get(player).doTransaction(((Tax) pieces[players.get(player).getPosition()]).getCost());
            this.playerStatus = Status.UNDECIDED;
            if (!dice.isDouble()) {
                endTurn();
            }
        } else if (!((Property) pieces[players.get(player).getPosition()]).isAvailable()) { // Property is not available
            // Player who owns the property gets rent
            if (((Property) pieces[players.get(player).getPosition()]).getOwner().equals(players.get(player))) { // If the player lands on themselves, do nothing
                outputText+="\nYou do not need to pay rent since you own this property.";
            } else {
                outputText+="\nTaking the money from your account";
                players.get(player).doTransaction(((Property) pieces[players.get(player).getPosition()]).getRent()); // Deducts the cost from account
                ((Property) pieces[players.get(player).getPosition()]).getOwner().setMoney(((Property) pieces[players.get(player).getPosition()]).getRent()); // adds the rent cost to the owner's account
            }
            if (!dice.isDouble()) {
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
            if ((!((Property) pieces[players.get(player).getPosition()]).isAvailable()) && (!(((Property) pieces[players.get(player).getPosition()]).getOwner().equals(players.get(player))))) { //property not available
                check = players.get(player).isBankrupt(((Property) pieces[players.get(player).getPosition()]).getRent());
            } else {
                check = (players.get(player).isBankrupt(((Property) pieces[players.get(player).getPosition()]).getPrice()));
            }
        }

        if (check) {
            outputText += "\nOh no you don't have enough money!\nYou have reached bankruptcy :( \nYou are being eliminated from the game";
            for (int i = 0; i < players.get(player).getProperties().size(); i++) {
                players.get(player).getProperties().get(i).sell();
            }
            if(getPlayers().size() > 2) {
                this.playerStatus = Status.BANKRUPT2;
                players.remove(player);
            }
            else{
                this.playerStatus = Status.BANKRUPT;
                players.remove(player);
                if (players.size() == 1) {
                    outputText += "\nGame Over! " + players.get(0).getName() + " has won the game! \nThanks for playing! ";
                }
            }
            notifyViews();
        }
        return check;
    }

    private void buy() {
        if (((Property) pieces[players.get(player).getPosition()]).isAvailable()) {
            players.get(player).doTransaction(((Property) pieces[players.get(player).getPosition()]).getPrice()); // price of property is deducted from player's account
            players.get(player).addProperty(((Property) pieces[players.get(player).getPosition()])); // property is added to player's account
            ((Property) pieces[players.get(player).getPosition()]).purchase(); //set property to unavailable
            ((Property) pieces[players.get(player).getPosition()]).setOwner(players.get(player)); //set owner
            outputText+="Successfully purchased!";
        }
        else {
            outputText= "\nUnfortunately the property you are on is not available for purchase.";
        }
    }

    /**
     * Method passes the turn to the next player in the game
     */
    public void endTurn() {
        players.get(player).resetNumDoublesRolled();
        player++;
        if (player > players.size() - 1) {
            player = 0;
        }
        outputText += "\nNow it's "+ (players.get(player).getName()) + " 's turn!";
        this.playerStatus= Status.UNDECIDED;
    }

    /**
     * Method displays the user interface of the Monopoly Board that takes user input
     */
    public void play(String command) {
        outputText = this.getPlayers().get(player).getName().toUpperCase(Locale.ROOT) + "'S TURN:\n";

        if (command.equals("Help")) {
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
        }
        else if (command.equals("Quit")) {
            this.playerStatus = Status.QUITTING;
        }
        notifyViews();
    }
}