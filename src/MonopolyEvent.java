import java.util.EventObject;

public class MonopolyEvent extends EventObject {

    private Dice dice;
    private String instruction;
    MonopolyModel.Status status;
    private int numPlayers;
    private int currentPlayer;


    public MonopolyEvent(MonopolyModel source) {
        super(source);
        this.dice = source.getDice();
        this.instruction = source.getOutputText();
        this.status = source.getPlayerStatus();
        this.numPlayers = source.getPlayers().size();
        this.currentPlayer = source.getCurrentPlayer();
    }

    public String getInstruction() {
        return instruction;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public Dice getDice() {
        return dice;
    }

}
