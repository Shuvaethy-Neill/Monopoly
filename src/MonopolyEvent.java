import java.io.Serializable;
import java.util.ArrayList;
import java.util.EventObject;

/**
 * MonopolyEvent Class that handles all events that
 * occur on the Monopoly Board
 *
 * @author Shuvaethy Neill
 * @verion 1.0
 * @since 2021-10-22
 */
public class MonopolyEvent extends EventObject implements Serializable {

    private Dice dice;
    private String instruction;
    MonopolyModel.Status status;
    private ArrayList currentPlayers;
    private int player;

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
        this.player = source.getPlayer();
    }

    /**
     * Method gets the current players actively playing
     * @return ArrayList, ArrayList of active players
     */
    public ArrayList getCurrentPlayers() {
        return currentPlayers;
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

    public int getPlayer(){
        return player;
    }

}
