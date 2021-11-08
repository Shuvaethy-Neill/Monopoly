import java.util.ArrayList;
import java.util.EventObject;

/**
 * MonopolyEvent Class that handles all events that occur on the Monopoly Board
 */
public class MonopolyEvent extends EventObject {

    private Dice dice;
    private String instruction;
    MonopolyModel.Status status;
    private ArrayList currentPlayers;
    private int currentPlayer;

    /**
     * Constructor for MonopolyEvent
     * @param source MonopolyModel , MVC model
     */
    public MonopolyEvent(MonopolyModel source) {
        super(source);
        this.dice = source.getDice();
        this.instruction = source.getOutputText();
        this.status = source.getPlayerStatus();
        this.currentPlayers = source.getPlayers();
        this.currentPlayer =  source.getPlayer();
    }

    /**
     * Method gets the current players actively playing
     * @return ArrayList, ArrayList of active players
     */
    public ArrayList getCurrentPlayers() {
        return currentPlayers;
    }

    /**
     * Method gets the current player actively playing
     * @return int, player number
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Method gets the list of instructions given to the player
     * @return String, display of instructions
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * Method gets the dices on the board
     * @return Dice, dices being played with
     */
    public Dice getDice() {
        return dice;
    }

}
