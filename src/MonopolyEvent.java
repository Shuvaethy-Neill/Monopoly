import java.util.ArrayList;
import java.util.EventObject;

public class MonopolyEvent extends EventObject {

    private Dice dice;
    private String instruction;
    MonopolyModel.Status status;
    private ArrayList currentPlayers;
    private int currentPlayer;

    public MonopolyEvent(MonopolyModel source) {
        super(source);
        this.dice = source.getDice();
        this.instruction = source.getOutputText();
        this.status = source.getPlayerStatus();
        this.currentPlayers = source.getPlayers();
        this.currentPlayer =  source.getPlayer();
    }

    public ArrayList getCurrentPlayers() {
        return currentPlayers;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public String getInstruction() {
        return instruction;
    }

    public Dice getDice() {
        return dice;
    }

}
